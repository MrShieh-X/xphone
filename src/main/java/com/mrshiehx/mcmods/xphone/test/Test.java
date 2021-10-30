package com.mrshiehx.mcmods.xphone.test;

public class Test {
    public static void main(String[] args) {
        System.out.println(calculateButtonGuiDirectionX(1));
        System.out.println(calculateButtonGuiDirectionX(2));
        System.out.println(calculateButtonGuiDirectionX(3));
        System.out.println(calculateButtonGuiDirectionX(4));
        System.out.println(calculateButtonGuiDirectionX(5));
        System.out.println(calculateButtonGuiDirectionX(6));
        System.out.println(calculateButtonGuiDirectionX(7));
        System.out.println(calculateButtonGuiDirectionX(8));
    }

    public static int calculateButtonGuiDirectionY(int order) {
        if (order <= 0) {
            throw new UnsupportedOperationException("the order starts from 1");
        } else {
            int a = order / 3;
            int b = order % 3;
            //if (a == 0) return INITIAL_BUTTON_DIRECTION_Y;
            if (b == 0) {
                --a;
            }

            return 400 + (20 * (a)) + (5 * a);
        }
    }


    public static int calculateButtonGuiDirectionX(int order){
        if(order<=0){
            throw new UnsupportedOperationException("the order starts from 1");
        }else {
            /*1,4,7,10,13,16,19,22,25;
            2,5,8,11,14,17,20,23,26;
            3,6,9,12,15,18,21,24,27;*/

            order=order+3;
            //int q=order-1;
            int w=order-2;
            int r=order-3;
            int t;
            if(r%3==0){
                t=3;
            } else if(w%3==0) {
                t = 2;
            } else{
                t=1;
            }
            return 400+((t-1)*20)+((t-1)*4);




            /*int a = order / 3;
            int b = order % 3;
            if (a == 0) return INITIAL_BUTTON_DIRECTION_X;*/
        }

    }
}
