/*package com.company;*/

public class Washtype {
    private int price;
    private String name;

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Washtype(String name, int price) {
        this.price = price;
        this.name = name;
    }

    public enum typeOfWash {
        SILVER("Silver", 100),
        GOLD("Gold", 200),
        PLATINIUM("Platinium", 300),
        DIAMOND("Diamond", 500);

        private String name;
        private int price;

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        typeOfWash(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public Washtype getObject() {
            Washtype lol = new Washtype(name, price);
            return lol;
        }

    }

}
