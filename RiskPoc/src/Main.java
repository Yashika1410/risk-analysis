

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main(String[] args) {
        Map<String, Map<String, Object>> data = new HashMap<>();
        Map<String, Object> item = new HashMap<>();
        item.put("information_security", 80);
        item.put("resilience", 60);
        item.put("conduct", 70);
        data.put("TCS", item);
        Map<String, Object> item_1 = new HashMap<>();
        item_1.put("information_security", 90);
        item_1.put("resilience", 50);
        item_1.put("conduct", 55);
        data.put("Wipro", item_1);

        Map<String, Object> weight = new HashMap<>();
        weight.put("information_security_weight", .4);
        weight.put("resilience_weight", .47);
        weight.put("conduct_weight", .13);
        Map<String, Object> formula = new HashMap<>();
        formula.put("information_security_cal_weight", "information_security * information_security_weight");
        formula.put("resilience_cal_weight", "resilience * resilience_weight");
        formula.put("conduct_cal_weight", "conduct * conduct_weight");
        Map<String, Object> result = new HashMap<>();
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
        data.forEach((k, v) -> {
            for (String key : v.keySet()) {
                String expression = key + "=" + v.get(key) + "; " + key + "_weight=" + weight.get(key + "_weight")
                        + "; " + formula.get(key + "_cal_weight") + ";";
                try {
                    Double res = (Double) scriptEngine.eval(expression);
                    result.put(key + "_res", res);
                } catch (ScriptException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.println("name " + k + result.toString());
            result.clear();
        });
    }
}
