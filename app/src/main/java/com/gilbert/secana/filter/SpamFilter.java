package com.gilbert.secana.filter;

import com.gilbert.secana.data.Call;
import com.gilbert.secana.data.Sms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Gilbert} on ${31/3/15}.
 */
public class SpamFilter {

    public static final List<String> areaCodes = new ArrayList<>();
    public static final List<String> keyWords = new ArrayList<>();

    public static int SmsFilter(Sms sms) {
        String number = sms.number;
        sms.level = 0;

        for (String code : areaCodes) {
            if (number.substring(0, code.length()).equals(code)) {
                sms.level++;
            }
        }

        for (String word : keyWords) {
            if (sms.content.contains(word)) {
                sms.level++;
            }
        }

        sms.level = sms.level > 4 ? 4 : sms.level;

        return (int) sms.level;
    }

    public static int CallFilter(Call call) {
        String number = call.number;
        call.level = 0;

        for (String code : areaCodes) {
            if (number.substring(0, code.length()).equals(code)) {
                call.level = 4;
            }
        }

        return (int) call.level;
    }
}
