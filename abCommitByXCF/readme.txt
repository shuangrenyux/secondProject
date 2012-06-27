说明：
    操作方法，详见效果截图。

    运行：
        控制台->目录“ab\target\”->运行命令“java -jar ab-0.0.1-SNAPSHOT.jar”

    生成的帮助文档位置：
        ab\target\site\apidocs\
    生成的测试评估报告位置：
        ab\target\site\cobertura\

关于完成情况：
    完成了所有功能性要求，包括实体的创建、命令的识别、search和delete时正则表达式的应用、xml文档的解析等。
    除测试覆盖率低于85%的要求以外，完成了所有非功能性要求，项目使用Maven3.0.4管理，使用Junit进行单元测试，使用Cobertura进行覆盖率检测并生成报告，
    javadoc生成帮助文档，使用JDK自身的DOM来解析XML文档，使用maven-assembly-plugin实现自定义打包。
    不足：
    由于App.java中的程序负责与控制台交互，限于时间原因，未深入研究Junit关于控制台交互的自动化测试问题，因而Cobertura生成的报告中覆盖率只达到了68%。