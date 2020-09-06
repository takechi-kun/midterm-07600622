package th.ac.su.cp.speedcalculator.check;

import java.util.Locale;

public class Check {
    public enum ResultCheck {
        pleaseInsert, nonzero, speedover , speeddefault;

    }
    public ResultCheck check (String text_meter,String text_second){
        ResultCheck result = null;
        String answer = null;
        if (text_meter.length() == 0|| text_second.length()==0){
            result = ResultCheck.pleaseInsert;
        }
        else if(text_meter.length() > 0 && text_second.equals("0")){
            result = ResultCheck.nonzero;
        }
        else{
            double myDouble1 = Double.parseDouble(text_meter);
            double myDouble2 = Double.parseDouble(text_second);
            double calculate  =  ((myDouble1/myDouble2)*18)/5;
            if(calculate > 80){
                result = ResultCheck.speedover;
            }
            else{
                result = ResultCheck.speeddefault;
                String str = String.format(
                        Locale.getDefault(), "%.2f", calculate
                );

            }
        }

        return result;
    }

}
