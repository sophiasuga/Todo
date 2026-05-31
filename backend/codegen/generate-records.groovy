/**
 * schema.graphqls を読んで Java Record を生成するスクリプト
 * mvn generate-sources で自動実行される
 */

def packageName  = "com.example.todo.generated"
def schemaFile   = new File(project.basedir, "src/main/resources/graphql/schema.graphqls")
def outputDir    = new File(project.build.directory, "generated-sources/graphql/com/example/todo/generated")

outputDir.mkdirs()

// GraphQL → Java の型マッピング
def typeMap = [
    'ID'     : 'String',
    'String' : 'String',
    'Int'    : 'Integer',
    'Float'  : 'Double',
    'Boolean': 'Boolean',
]

// Query / Mutation / Subscription は Record 生成しない
def skipTypes = ['Query', 'Mutation', 'Subscription']

def schemaText = schemaFile.text

// type Foo { ... }  /  input Bar { ... } のブロックを抽出
def blockRe = ~/(?s)(?:type|input)\s+(\w+)(?:\s+implements[\w\s&]+)?\s*\{(.*?)\}/
def fieldRe  = ~/^\s*(\w+)\s*:\s*(\[?)(\w+)(!?\]?!?)/

(schemaText =~ blockRe).each { full, typeName, body ->
    if (typeName in skipTypes) return

    def fields = []
    body.split('\n').each { line ->
        def m = (line =~ fieldRe)
        if (!m) return
        def fname      = m[0][1]
        def isList     = m[0][2] == '['
        def gqlType    = m[0][3]
        def javaType   = typeMap.getOrDefault(gqlType, gqlType)
        if (isList) javaType = "java.util.List<${javaType}>"
        fields << "    ${javaType} ${fname}"
    }

    if (fields.isEmpty()) return

    def content = """\
package ${packageName};

@javax.annotation.processing.Generated(value = "generate-records.groovy")
public record ${typeName}(
${fields.join(',\n')}
) {}
"""
    new File(outputDir, "${typeName}.java").text = content
    log.info("[codegen] Generated record: ${typeName}.java")
}
