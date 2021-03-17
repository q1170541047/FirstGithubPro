package com.yiche.baselibrary.util;

import android.graphics.Color;
import android.net.ParseException;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;


/**
 * crated at 2016/10/11 17:43
 *
 * @name 字符串工具类
 * @description 对字符串进行相应的处理，及一些判断
 */
public class StringUtil {
    /**
     * 中国公民身份证号码最小长度。
     */
    public static final int CHINA_ID_MIN_LENGTH = 15;
    /**
     * 中国公民身份证号码最大长度。
     */
    public static final int CHINA_ID_MAX_LENGTH = 18;
    /**
     * 省、直辖市代码表
     */
    public static final String cityCode[] = {"11", "12", "13", "14", "15",
            "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41",
            "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61",
            "62", "63", "64", "65", "71", "81", "82", "91"};
    /**
     * 每位加权因子
     */
    public static final int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9,
            10, 5, 8, 4, 2};
    /**
     * 第18位校检码
     */
    public static final String verifyCode[] = {"1", "0", "X", "9", "8", "7",
            "6", "5", "4", "3", "2"};
    /**
     * 最低年限
     */
    public static final int MIN = 1930;
    public static Map<String, String> cityCodes = new HashMap<String, String>();
    /**
     * 台湾身份首字母对应数字
     */
    public static Map<String, Integer> twFirstCode = new HashMap<String, Integer>();
    /**
     * 香港身份首字母对应数字
     */
    public static Map<String, Integer> hkFirstCode = new HashMap<String, Integer>();

    static {
        cityCodes.put("11", "北京");
        cityCodes.put("12", "天津");
        cityCodes.put("13", "河北");
        cityCodes.put("14", "山西");
        cityCodes.put("15", "内蒙古");
        cityCodes.put("21", "辽宁");
        cityCodes.put("22", "吉林");
        cityCodes.put("23", "黑龙江");
        cityCodes.put("31", "上海");
        cityCodes.put("32", "江苏");
        cityCodes.put("33", "浙江");
        cityCodes.put("34", "安徽");
        cityCodes.put("35", "福建");
        cityCodes.put("36", "江西");
        cityCodes.put("37", "山东");
        cityCodes.put("41", "河南");
        cityCodes.put("42", "湖北");
        cityCodes.put("43", "湖南");
        cityCodes.put("44", "广东");
        cityCodes.put("45", "广西");
        cityCodes.put("46", "海南");
        cityCodes.put("50", "重庆");
        cityCodes.put("51", "四川");
        cityCodes.put("52", "贵州");
        cityCodes.put("53", "云南");
        cityCodes.put("54", "西藏");
        cityCodes.put("61", "陕西");
        cityCodes.put("62", "甘肃");
        cityCodes.put("63", "青海");
        cityCodes.put("64", "宁夏");
        cityCodes.put("65", "新疆");
        cityCodes.put("71", "台湾");
        cityCodes.put("81", "香港");
        cityCodes.put("82", "澳门");
        cityCodes.put("91", "国外");
        twFirstCode.put("A", 10);
        twFirstCode.put("B", 11);
        twFirstCode.put("C", 12);
        twFirstCode.put("D", 13);
        twFirstCode.put("E", 14);
        twFirstCode.put("F", 15);
        twFirstCode.put("G", 16);
        twFirstCode.put("H", 17);
        twFirstCode.put("J", 18);
        twFirstCode.put("K", 19);
        twFirstCode.put("L", 20);
        twFirstCode.put("M", 21);
        twFirstCode.put("N", 22);
        twFirstCode.put("P", 23);
        twFirstCode.put("Q", 24);
        twFirstCode.put("R", 25);
        twFirstCode.put("S", 26);
        twFirstCode.put("T", 27);
        twFirstCode.put("U", 28);
        twFirstCode.put("V", 29);
        twFirstCode.put("X", 30);
        twFirstCode.put("Y", 31);
        twFirstCode.put("W", 32);
        twFirstCode.put("Z", 33);
        twFirstCode.put("I", 34);
        twFirstCode.put("O", 35);
        hkFirstCode.put("A", 1);
        hkFirstCode.put("B", 2);
        hkFirstCode.put("C", 3);
        hkFirstCode.put("R", 18);
        hkFirstCode.put("U", 21);
        hkFirstCode.put("Z", 26);
        hkFirstCode.put("X", 24);
        hkFirstCode.put("W", 23);
        hkFirstCode.put("O", 15);
        hkFirstCode.put("N", 14);
    }


    /**
     * 验证身份证是否合法
     */
    public static boolean validateCard(String idCard) {
        String card = idCard.trim();
        if (validateIdCard18(card)) {
            return true;
        }
        if (validateIdCard15(card)) {
            return true;
        }
        String[] cardval = validateIdCard10(card);
        if (cardval != null) {
            if (cardval[2] != null && cardval[2].equals("true")) {
                return true;
            }
        }
        return false;
    }

    /**
     * 验证18位身份编码是否合法
     *
     * @param idCard 身份编码
     * @return 是否合法
     */
    public static boolean validateIdCard18(String idCard) {
        boolean bTrue = false;
        if (idCard.length() == CHINA_ID_MAX_LENGTH) {
            // 前17位
            String code17 = idCard.substring(0, 17);
            // 第18位
            String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
            if (isNum(code17)) {
                char[] cArr = code17.toCharArray();
                if (cArr != null) {
                    int[] iCard = converCharToInt(cArr);
                    int iSum17 = getPowerSum(iCard);
                    // 获取校验位
                    String val = getCheckCode18(iSum17);
                    if (val.length() > 0) {
                        if (val.equalsIgnoreCase(code18)) {
                            bTrue = true;
                        }
                    }
                }
            }
        }
        return bTrue;
    }

    /**
     * 验证15位身份编码是否合法
     *
     * @param idCard 身份编码
     * @return 是否合法
     */
    public static boolean validateIdCard15(String idCard) {
        if (idCard.length() != CHINA_ID_MIN_LENGTH) {
            return false;
        }
        if (isNum(idCard)) {
            String proCode = idCard.substring(0, 2);
            if (cityCodes.get(proCode) == null) {
                return false;
            }
            String birthCode = idCard.substring(6, 12);
            Date birthDate = null;
            try {
                birthDate = new SimpleDateFormat("yy").parse(birthCode
                        .substring(0, 2));
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            if (birthDate != null)
                cal.setTime(birthDate);
            if (!valiDate(cal.get(Calendar.YEAR),
                    Integer.valueOf(birthCode.substring(2, 4)),
                    Integer.valueOf(birthCode.substring(4, 6)))) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 验证10位身份编码是否合法
     *
     * @param idCard 身份编码
     * @return 身份证信息数组
     * <p>
     * [0] - 台湾、澳门、香港 [1] - 性别(男M,女F,未知N) [2] - 是否合法(合法true,不合法false)
     * 若不是身份证件号码则返回null
     * </p>
     */
    public static String[] validateIdCard10(String idCard) {
        String[] info = new String[3];
        String card = idCard.replaceAll("[\\(|\\)]", "");
        if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
            return null;
        }
        if (idCard.matches("^[a-zA-Z][0-9]{9}$")) { // 台湾
            info[0] = "台湾";
            String char2 = idCard.substring(1, 2);
            if (char2.equals("1")) {
                info[1] = "M";
            } else if (char2.equals("2")) {
                info[1] = "F";
            } else {
                info[1] = "N";
                info[2] = "false";
                return info;
            }
            info[2] = validateTWCard(idCard) ? "true" : "false";
        } else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$")) { // 澳门
            info[0] = "澳门";
            info[1] = "N";
            // TODO
        } else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) { // 香港
            info[0] = "香港";
            info[1] = "N";
            info[2] = validateHKCard(idCard) ? "true" : "false";
        } else {
            return null;
        }
        return info;
    }

    /**
     * 验证台湾身份证号码
     *
     * @param idCard 身份证号码
     * @return 验证码是否符合
     */
    public static boolean validateTWCard(String idCard) {
        String start = idCard.substring(0, 1);
        String mid = idCard.substring(1, 9);
        String end = idCard.substring(9, 10);
        Integer iStart = twFirstCode.get(start);
        Integer sum = iStart / 10 + (iStart % 10) * 9;
        char[] chars = mid.toCharArray();
        Integer iflag = 8;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iflag;
            iflag--;
        }
        return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end) ? true
                : false;
    }

    /**
     * 验证香港身份证号码(存在Bug，部份特殊身份证无法检查)
     * <p>
     * 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，对应数字58 前2位英文字符A-Z分别对应数字10-35
     * 最后一位校验码为0-9的数字加上字符"A"，"A"代表10
     * </p>
     * <p>
     * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效
     * </p>
     *
     * @param idCard 身份证号码
     * @return 验证码是否符合
     */
    public static boolean validateHKCard(String idCard) {
        String card = idCard.replaceAll("[\\(|\\)]", "");
        Integer sum = 0;
        if (card.length() == 9) {
            sum = (Integer.valueOf(card.substring(0, 1).toUpperCase()
                    .toCharArray()[0]) - 55)
                    * 9
                    + (Integer.valueOf(card.substring(1, 2).toUpperCase()
                    .toCharArray()[0]) - 55) * 8;
            card = card.substring(1, 9);
        } else {
            sum = 522 + (Integer.valueOf(card.substring(0, 1).toUpperCase()
                    .toCharArray()[0]) - 55) * 8;
        }
        String mid = card.substring(1, 7);
        String end = card.substring(7, 8);
        char[] chars = mid.toCharArray();
        Integer iflag = 7;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iflag;
            iflag--;
        }
        if (end.toUpperCase().equals("A")) {
            sum = sum + 10;
        } else {
            sum = sum + Integer.valueOf(end);
        }
        return (sum % 11 == 0) ? true : false;
    }

    /**
     * 将字符数组转换成数字数组
     *
     * @param ca 字符数组
     * @return 数字数组
     */
    public static int[] converCharToInt(char[] ca) {
        int len = ca.length;
        int[] iArr = new int[len];
        try {
            for (int i = 0; i < len; i++) {
                iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return iArr;
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param iArr
     * @return 身份证编码。
     */
    public static int getPowerSum(int[] iArr) {
        int iSum = 0;
        if (power.length == iArr.length) {
            for (int i = 0; i < iArr.length; i++) {
                for (int j = 0; j < power.length; j++) {
                    if (i == j) {
                        iSum = iSum + iArr[i] * power[j];
                    }
                }
            }
        }
        return iSum;
    }

    /**
     * 将power和值与11取模获得余数进行校验码判断
     *
     * @param iSum
     * @return 校验位
     */
    public static String getCheckCode18(int iSum) {
        String sCode = "";
        switch (iSum % 11) {
            case 10:
                sCode = "2";
                break;
            case 9:
                sCode = "3";
                break;
            case 8:
                sCode = "4";
                break;
            case 7:
                sCode = "5";
                break;
            case 6:
                sCode = "6";
                break;
            case 5:
                sCode = "7";
                break;
            case 4:
                sCode = "8";
                break;
            case 3:
                sCode = "9";
                break;
            case 2:
                sCode = "x";
                break;
            case 1:
                sCode = "0";
                break;
            case 0:
                sCode = "1";
                break;
        }
        return sCode;
    }


    /**
     * 数字验证
     *
     * @param val
     * @return 提取的数字。
     */
    public static boolean isNum(String val) {
        return val == null || "".equals(val) ? false : val.matches("^[0-9]*$");
    }

    /**
     * 验证小于当前日期 是否有效
     *
     * @param iYear  待验证日期(年)
     * @param iMonth 待验证日期(月 1-12)
     * @param iDate  待验证日期(日)
     * @return 是否有效
     */
    public static boolean valiDate(int iYear, int iMonth, int iDate) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int datePerMonth;
        if (iYear < MIN || iYear >= year) {
            return false;
        }
        if (iMonth < 1 || iMonth > 12) {
            return false;
        }
        switch (iMonth) {
            case 4:
            case 6:
            case 9:
            case 11:
                datePerMonth = 30;
                break;
            case 2:
                boolean dm = ((iYear % 4 == 0 && iYear % 100 != 0) || (iYear % 400 == 0))
                        && (iYear > MIN && iYear < year);
                datePerMonth = dm ? 29 : 28;
                break;
            default:
                datePerMonth = 31;
        }
        return (iDate >= 1) && (iDate <= datePerMonth);
    }


    /**
     * 时间戳格式化
     *
     * @param m      时间戳
     * @param format yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatTime(long m, String format) {
        SimpleDateFormat df3 = new SimpleDateFormat(format);
        String date = df3.format(new Date(m * 1000));
        return date;

    }


    private static final long ONE_MINUTE = 60000L;
    private static final long ONE_HOUR = 3600000L;
    private static final long ONE_DAY = 86400000L;
    private static final long ONE_WEEK = 604800000L;

    private static final String ONE_SECOND_AGO = "秒前";
    private static final String ONE_MINUTE_AGO = "分钟前";
    private static final String ONE_HOUR_AGO = "小时前";
    private static final String ONE_DAY_AGO = "天前";
    private static final String ONE_MONTH_AGO = "月前";
    private static final String ONE_YEAR_AGO = "年前";

    /**
     * @param times 时间戳
     * @return
     */
    public static String format(long times) {
        Date date = new Date(times);

        long delta = new Date().getTime() - date.getTime();
        if (delta < 1L * ONE_MINUTE) {
            long seconds = toSeconds(delta);
            return (seconds <= 0 ? 1 : seconds) + ONE_SECOND_AGO;
        }
        if (delta < 45L * ONE_MINUTE) {
            long minutes = toMinutes(delta);
            return (minutes <= 0 ? 1 : minutes) + ONE_MINUTE_AGO;
        }
        if (delta < 24L * ONE_HOUR) {
            long hours = toHours(delta);
            return (hours <= 0 ? 1 : hours) + ONE_HOUR_AGO;
        }
        if (delta < 48L * ONE_HOUR) {
            return "昨天";
        }
        if (delta < 30L * ONE_DAY) {
            long days = toDays(delta);
            return (days <= 0 ? 1 : days) + ONE_DAY_AGO;
        }
        if (delta < 12L * 4L * ONE_WEEK) {
            long months = toMonths(delta);
            return (months <= 0 ? 1 : months) + ONE_MONTH_AGO;
        } else {
            long years = toYears(delta);
            return (years <= 0 ? 1 : years) + ONE_YEAR_AGO;
        }
    }

    private static long toSeconds(long date) {
        return date / 1000L;
    }

    private static long toMinutes(long date) {
        return toSeconds(date) / 60L;
    }

    private static long toHours(long date) {
        return toMinutes(date) / 60L;
    }

    private static long toDays(long date) {
        return toHours(date) / 24L;
    }

    private static long toMonths(long date) {
        return toDays(date) / 30L;
    }

    private static long toYears(long date) {
        return toMonths(date) / 365L;
    }


    /**
     * get 方式请求url拼接
     *
     * @return
     */
    public static String appendGetUrl(LinkedHashMap<String, Object> params, String url) {

        StringBuffer buffer = new StringBuffer();
        Iterator<?> iterator = params.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<?, ?> entry = (Entry<?, ?>) iterator.next();
            Object key = entry.getKey();
            Object value = entry.getValue();
            try {
                value = URLEncoder.encode(
                        value == null ? "" : value.toString(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            buffer.append(key);
            buffer.append("=");
            buffer.append(value);
            buffer.append("&");
        }


        String newUrl = url + "?" + buffer.toString();

        return newUrl;

    }

    /**
     * 随机码
     */
    public static String getRangeNumber() {

        return System.currentTimeMillis() + new Random().nextInt(1000000000) + "";
    }

    /**
     * MD5 加密
     *
     * @param str
     * @return
     */
    public static String StringMD5(String str) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.reset();
        md.update(str.getBytes());
        byte[] bArray = md.digest();
        String finalStr = new String(encodeHex(bArray, hexDigits));
//        System.out.println("16位大写:" + finalStr.substring(8, 24));
//        System.out.println("32位大写:" + finalStr);
        return finalStr;
    }

    protected static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int len = data.length;
        final char[] out = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            System.out.println((0xF0 & data[i]) >>> 4);
            out[j++] = toDigits[0x0F & data[i]];
        }
        return out;
    }


    /**
     * @param num:要获取二进制值的数
     * @param index:倒数第一位为0，依次类推
     */
    private static int get(int num, int index) {
        return (num & (0x1 << index)) >> index;
    }


    public static String phoneAddLine(String phone) {
        if (phone.length() >= 11) {
            StringBuffer stringBuffer = new StringBuffer(phone);
            stringBuffer.insert(3, "-");
            stringBuffer.insert(8, "-");
            return stringBuffer.toString();
        } else {
            return phone;
        }

    }

    public static String getFileSize(String path) {
        File f = new File(path);
        if (!f.exists()) {

            return "0 MB";
        } else {
            long size = f.length();
            java.text.DecimalFormat df = new java.text.DecimalFormat("#.0");
            String format = df.format((size / 1024f) / 1024f);
            if (format.startsWith(".")) {
                return "0" + format + "MB";
            }
            return format + "MB";
        }
    }

//    public static String getFileSize1(String path) {
//        File f = new File(path);
//        if (!f.exists()) {

    //            return "0";
//        } else {
//            long size = f.length();
//            java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
//            String format = df.format((size / 1024f) / 1024f);
//            if(format.startsWith(".")){
//                return "0"+format;
//            }
//            return format;
//        }
//    }
    public static float getMyFileSize(File file) throws Exception {
        float size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available() / 1024f / 1024f;
        } else {
            file.createNewFile();
            Log.e("获取文件大小", "文件不存在!");
        }
        return (float) Math.round(size * 100) / 100;
    }

    /**
     * @param
     * @time 2018/5/22
     * @describe null返回""
     */
    public static String getString(String str) {
        try {
            if (str.equals("null")) {
                return "";
            }
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getVerifyString(String str) {
        try {
            if (str.equals("null")) {
                return "";
            }
            if (str.equals("--")||str.equals("无")) {
                return "";
            }
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getStringSelect(String str, String hint) {
        if (getString(str).isEmpty()) {
            return hint;
        }
        return str;
    }

    public static String getReplaceString(String str) {
        String replace = "- -";
        if (str == null) {
            return replace;
        }
        try {
            if (str.equals("null")) {
                return replace;
            }
            if (str.isEmpty()) {
                return replace;
            }
            return str;
        } catch (Exception e) {
            return replace;
        }
    }
    public static String getReplaceString(String str,boolean enable) {
        if (enable) {
            try {
                if (str.equals("null")) {
                    return "";
                }
                return str;
            } catch (Exception e) {
                return "";
            }
        }
        String replace = "- -";
        if (str == null) {
            return replace;
        }
        try {
            if (str.equals("null")) {
                return replace;
            }
            if (str.isEmpty()) {
                return replace;
            }
            return str;
        } catch (Exception e) {
            return replace;
        }
    }
    public static String getString(String str,String replace) {
        if (str.equals(replace)){
            return "";
        }
        return getString(str);
    }

    public static String getReplaceString(String str, EditText textView, boolean enable) {
        if (enable) {
            try {
                if (str.equals("null")) {
                    return "";
                }
                return str;
            } catch (Exception e) {
                return "";
            }
        }
        String replace = "- -";
        if (str == null) {
            textView.setTextColor(Color.parseColor("#999999"));
            return replace;
        }
        try {
            if (str.equals("null")) {
                textView.setTextColor(Color.parseColor("#999999"));
                return replace;
            }
            if (str.isEmpty()) {
                textView.setTextColor(Color.parseColor("#999999"));
                return replace;
            }
            return str;
        } catch (Exception e) {
            return replace;
        }
    }

    public static String getReplaceString(String str, TextView textView, boolean enable) {
        if (enable) {
            try {
                if (str.equals("null")) {
                    return "";
                }
                return str;
            } catch (Exception e) {
                return "";
            }
        }
        String replace = "- -";
        if (str == null) {
            textView.setTextColor(Color.parseColor("#999999"));
            return replace;
        }
        try {
            if (str.equals("null")) {
                textView.setTextColor(Color.parseColor("#999999"));
                return replace;
            }
            if (str.isEmpty()) {
                textView.setTextColor(Color.parseColor("#999999"));
                return replace;
            }
            return str;
        } catch (Exception e) {
            return replace;
        }
    }

    public static String getReplaceString(String str, String replace) {
        if (str == null) {
            return replace;
        }
        try {
            if (str.equals("null")) {
                return replace;
            }
            if (str.isEmpty()) {
                return replace;
            }
            return str;
        } catch (Exception e) {
            return replace;
        }
    }
}
