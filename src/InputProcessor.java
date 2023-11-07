import java.util.ArrayList;
import java.util.StringJoiner;

public class InputProcessor {


    public static String generateOutput(String appName, String envConfig, String port){
        String[] contents = parseJson(envConfig);
        return buildRunCommand(appName, contents, port);
    }

    /**
     * Parses input configuration json file
     * @param input
     * @return
     */
    private static String[] parseJson(String input) {
        input = input.replaceAll("[{}\" ]", "");
        String[] tmp = input.split(",");
        ArrayList<String> arrayList = new ArrayList<>(tmp.length);

        for (String item : tmp) {
            item = item.replaceFirst(":", "=");
            if (!item.isEmpty()) {
                arrayList.add(item);
            }
        }

        return arrayList.toArray(new String[0]);
    }

    /**
     * Forms the build command
     * @param appName
     * @param contents
     * @param port
     * @return
     */
    private static String buildRunCommand(String appName, String[] contents, String port) {
        StringJoiner joiner = new StringJoiner(" -e ");

        for (String content : contents) {
            if (!content.isEmpty()) {
                joiner.add(content);
            }
        }

        return "docker run -it -p " + port + ":" + port + " -e " + joiner + " " + appName + " bash";
    }
}