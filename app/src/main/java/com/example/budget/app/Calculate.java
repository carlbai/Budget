package com.example.budget.app;

/**
 * Created by carlbai on 4/8/14.
 */

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;
import java.util.Locale;

public class Calculate
{
    private static Symbols mSymbols = new Symbols();
    private static final String NAN      = "NaN";
    private static boolean mIsError = false;
    private static int mLineLength = 20;
    private static final String INFINITY = "Infinity";
    static final char MINUS = '\u2212';
    private static final String INFINITY_UNICODE = "\u221e";


    private static final String DIGITS = "0123456789.";
    private static final String OPERATOR = "+-/*";

    public static String evaluate(String input) throws SyntaxException{
        if(input.trim().equals(""))
        {
            return "";
        }

        int size = input.length();
        while(size > 0 && isOperator(input.charAt(size -1))){
            input = input.substring(0, size -1);
            -- size;
        }
        double value = mSymbols.eval(input);

        String result = "";
        for (int precision = mLineLength; precision > 6; precision--) {
            result = tryFormattingWithPrecision(value, precision);
            if (result.length() <= mLineLength) {
                break;
            }
        }
        return result.replace('-', MINUS).replace(INFINITY, INFINITY_UNICODE);
    }
    static boolean isOperator(String text) {
        return text.length() == 1 && isOperator(text.charAt(0));
    }

    static boolean isOperator(char c) {
        //plus minus times div

        return "+\u2212\u00d7\u00f7/*".indexOf(c) != -1;
    }

    private static String tryFormattingWithPrecision(double value, int precision) {
        // The standard scientific formatter is basically what we need. We will
        // start with what it produces and then massage it a bit.
        String result = String.format(Locale.US, "%" + mLineLength + "." + precision + "g", value);
        if (result.equals(NAN)) { // treat NaN as Error
            mIsError = true;
            return "Error";
        }
        String mantissa = result;
        String exponent = null;
        int e = result.indexOf('e');
        if (e != -1) {
            mantissa = result.substring(0, e);

            // Strip "+" and unnecessary 0's from the exponent
            exponent = result.substring(e + 1);
            if (exponent.startsWith("+")) {
                exponent = exponent.substring(1);
            }
            exponent = String.valueOf(Integer.parseInt(exponent));
        } else {
            mantissa = result;
        }

        int period = mantissa.indexOf('.');
        if (period == -1) {
            period = mantissa.indexOf(',');
        }
        if (period != -1) {
            // Strip trailing 0's
            while (mantissa.length() > 0 && mantissa.endsWith("0")) {
                mantissa = mantissa.substring(0, mantissa.length() - 1);
            }
            if (mantissa.length() == period + 1) {
                mantissa = mantissa.substring(0, mantissa.length() - 1);
            }
        }

        if (exponent != null) {
            result = mantissa + 'e' + exponent;
        } else {
            result = mantissa;
        }
        return result;
    }


    public static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);

            // it worked, so it is a number
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
