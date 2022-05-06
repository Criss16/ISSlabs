package model;

public enum TipStare {
    imprumutat,disponibil;

    public static TipStare parseEnum(String s){
        if (s.equals("imprumutat"))
            return imprumutat;
        if (s.equals("disponibil"))
            return disponibil;
        return null;
    }
}
