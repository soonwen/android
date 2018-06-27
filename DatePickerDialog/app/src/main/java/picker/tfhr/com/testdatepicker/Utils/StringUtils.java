package picker.tfhr.com.testdatepicker.Utils;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author huangsf
 * 功能描述：字符处理
 */

public class StringUtils {

    public static final String TAG = "StringUtils";
    public StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0)
            return true;
        for (int i = 0; i < strLen; i++)
            if (!Character.isWhitespace(str.charAt(i)))
                return false;

        return true;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }


    //
    public static String exceptionToString(Throwable th) {
        ByteArrayOutputStream bop = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bop);
        th.printStackTrace(ps);
        return new String(bop.toByteArray());
    }

    //从左到右每三个添加一个逗号
    public static String FormatString(String mString) {
        mString = new StringBuffer(mString).reverse().toString(); // 先将字符串颠倒顺序
        String str2 = "";

        int size = (mString.length() % 3 == 0) ? (mString.length() / 3) : (mString.length() / 3 + 1); // 每三位取一长度

        /*
         * 比如把一段字符串分成n段,第n段可能不是三个数,有可能是一个或者两个,
         * 现将字符串分成两部分.一部分为前n-1段,第二部分为第n段.前n-1段，每一段加一",".而第n段直接取出即可
         */
        for (int i = 0; i < size - 1; i++) { // 前n-1段
            str2 += mString.substring(i * 3, i * 3 + 3) + ",";
        }

        for (int i = size - 1; i < size; i++) { // 第n段
            str2 += mString.substring(i * 3, mString.length());
        }

        str2 = new StringBuffer(str2).reverse().toString();

        return str2;
    }


    //字符串判断是否是float或者int型
    public static boolean isInt(Object obj) {
        if (obj instanceof Integer || obj instanceof Float) {
            return true;
        }
        return false;
    }

    public static boolean checkNumber(String numStr) {
        if (numStr == null) {
            return false;
        }

        if (numStr == "") {
            return false;
        }
        if (numStr.contains(".")) {//验证是否是float型
            if (numStr.indexOf('.') == numStr.lastIndexOf('.')) {
                StringTokenizer st = new StringTokenizer(numStr, ".");
                while (st.hasMoreElements()) {
                    String splitStr = st.nextToken();
                    for (int i = splitStr.length(); --i >= 0; ) {
                        if (!Character.isDigit(splitStr.charAt(i))) {
                            return false;
                        }
                    }
                }
            }
        } else {
            for (int i = numStr.length(); --i >= 0; ) {//验证是否是int型
                if (!Character.isDigit(numStr.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 不够两位数补0
     *
     * @param id
     * @return
     */
    public static String getString(int id) {
        if (id != 0 && id < 10) {
            String str = String.format("%02d", id);
            return str;
        }
        return id + "";
    }

    /**
     * 判断map中value是否为空
     *
     * @param key
     * @param map
     * @return
     */
    public static String getValue(String key, Map map) {
        String value = "";
        if (map != null) {
            if (map.containsKey((Object) key)) {
                value = String.valueOf(map.get(key));
                if (value != null) {
                    if (value.equals("null"))
                        value = "";
                } else
                    value = "";
            }
        }
        return value;
    }

    /**
     * 判断map中是否有这个key值
     *
     * @param map
     * @param key
     * @return
     */
    public static List<String> getListdata(Map map, String key) {
        List<String> list = null;
        if (map != null) {
            if (map.containsKey(key)) {
                return (List<String>) map.get(key);
            }
        }
        return list;
    }

    /**
     * 去掉多余的0
     * @param num
     * @return
     */
    public static String deleteBySpot(String num) {
        String str = "";
        if (!TextUtils.isEmpty(num)) {
            if (num.indexOf(".") > 0) {
                str = num.replaceAll("0+?$", "");//去掉多余的0
                str = str.replaceAll("[.]$", "");
            } else {
                str = num;
            }
        }

        return str;
    }

    /**
     * 判断字符串是否都是数字
     * @param mBh
     * @return
     */
    public static boolean isNum(String mBh){
        if (!TextUtils.isEmpty(mBh)){
            return false;
        }
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher matcher = pattern.matcher(mBh);
        boolean matches = matcher.matches();
        if (matches){
        } else {
        }
        return matches;

    }
    public static void main(String[] args) {

    }
}
