package srbn.Generators;

public class HtmlMainGenerator {

    public static String headHTML = """
                        
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">                        
            """;

    public static String clsHeadHTML = """
                        
            </head>
            <body>
                        
            """;

    public static String clsBodyHTML = """
                            
            </body>
            </html>
                            
            """;

    public static StringBuilder getScript(String site, String page, String parent) {
        StringBuilder script = new StringBuilder();
        script.append("    <script>\n");

        script.append("        fetch('http://127.0.0.1:82', {\n");
        script.append("            method: 'POST',\n");
        script.append("            headers: {\n");
        script.append("                'Content-Type': 'text/plain',\n");
        script.append("            },\n");
        script.append(getInfo(site, page, parent));
        script.append("        })\n");
        script.append("            .then(response => {\n");
        script.append("                if (response.ok) {\n");
        script.append("                    console.log('Contador enviado al servidor Java');\n");
        script.append("                }\n                else {\n");
        script.append("                    console.error('Error al enviar el contador al servidor Java');\n");
        script.append("\n                }\n");
        script.append("            })\n");
        script.append("            .catch(error => {\n");
        script.append("                console.error('Error en la solicitud fetch:', error);\n");
        script.append("            });\n");
        script.append("    </script>\n");
        return script;
    }

    private static StringBuilder getInfo(String site, String page, String parent) {
        StringBuilder info = new StringBuilder();
        if(page.isEmpty() && parent.isEmpty()){
            info.append("body: \"{").append(site).append("}\",\n");
        } else if(!page.isEmpty() && parent.isEmpty()){
            info.append("body: \"{").append(site).append(",").append(page).append("}\",\n");
        } else {
            info.append("body: \"{").append(site).append(",").append(page).append(",").append(parent).append("}\",\n");
        }
        return info;
    }

}
