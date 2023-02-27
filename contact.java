import java.util.HashMap;

public class contact {
    private String name;
    private String number;
//    private HashMap<String, Integer> list;

//    public contact(String name, String number){
//        this.name = name;
//        this.number = formatNumber(number);
//    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public String formatNumber(String num){
        String fnumber = num;
        if (num.length()== 7){
            fnumber = num.replaceFirst("(\\d{3})(\\d+)", "$1-$2");
        } else if (num.length()==10){
            fnumber = num.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
        } else if (num.length() > 10){
//
            fnumber ="+" + num.substring(0,num.length()-10) + " " + num.substring(num.length()-10).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
        } else if (num.length() > 13){
           fnumber = "sorry this number input is incorrect";
        }
        return fnumber;
    };

    public String getInfo() {
        return name + number;
    }

    //    public HashMap<String, String> getList() {
//        HashMap<String, String> list = new HashMap<>() {
//            {
//                put(name, formatNumber());
//            }
//
//        };
//        return list;
//    }
}

