package com.arepadeobiri.mint;


import android.text.Editable;
import android.text.TextWatcher;

public class CreditCardWatcher implements TextWatcher {

    OnDigitsEnteredListener listener;


    public CreditCardWatcher(OnDigitsEnteredListener listener) {
        this.listener = listener;
    }

    interface OnDigitsEnteredListener {
        void callCardValidation(String cardNumber);
    }


    private static final int TOTAL_SYMBOLS = 19; // size of pattern 0000-0000-0000-0000
    private static final int TOTAL_DIGITS = 16; // max numbers of digits in pattern: 0000 x 4
    private static final int DIVIDER_MODULO = 5; // means divider position is every 5th symbol beginning with 1
    private static final int DIVIDER_POSITION = DIVIDER_MODULO - 1; // means divider position is every 4th symbol beginning with 0
    private static final char DIVIDER = '-';

    private static final char space = ' ';

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // noop
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // noop
    }

    @Override
    public void afterTextChanged(Editable s) {
        // Remove all spacing char
        int pos = 0;
        while (true) {
            if (pos >= s.length()) break;
            if (space == s.charAt(pos) && (((pos + 1) % 5) != 0 || pos + 1 == s.length())) {
                s.delete(pos, pos + 1);
            } else {
                pos++;
            }
        }

        // Insert char where needed.
        pos = 4;
        while (true) {
            if (pos >= s.length()) break;
            final char c = s.charAt(pos);
            // Only if its a digit where there should be a space we insert a space
            if ("0123456789".indexOf(c) >= 0) {
                s.insert(pos, "" + space);
            }
            pos += 5;
        }


        if (s.toString().length() == 7){
            listener.callCardValidation(s.toString());
        }
    }

}

