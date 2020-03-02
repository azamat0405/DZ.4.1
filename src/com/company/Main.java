package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 600;
    public static int bossDamage = 50;
    public static String bossDeffenceType = "";

    public static int[] heroesHealth = {250, 250, 250, 250};
    public static int[] heroesDamage = {20, 20, 20};
    public static String[] heroesAttackType = {"Physikal", "Magical", "Mental", "Medic"};

    public static void main(String[] args) {
        printStatistic();
        while (!isFinished()) {
            round();
        }
    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        bossHit();
        healHeroues();
        printStatistic();
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length);
        bossDeffenceType = heroesAttackType[randomIndex];
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes  won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                Random r = new Random();
                bossDamage = r.nextInt(110);
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0) {
                if (bossDeffenceType.equals(heroesAttackType[i])) {
                    Random r = new Random();
                    int coef = r.nextInt(8) + 2;
                    if (i == 3) {
                   //     int indexMinHeroesHealth = medHeroesHealth();
                       // if (heroesHealth[indexMinHeroesHealth] < 100) {
                       //     heroesHealth[indexMinHeroesHealth] += heroesDamage[3] * coef;
                      //  }

                    } else {
                        if (heroesHealth[i] > 0) {
                            if (bossDeffenceType.equals(heroesAttackType[i])) {
                            }
                            if (bossHealth - heroesDamage[i] * coef < 0) {
                                bossHealth = 0;
                            } else {
                                bossHealth = bossHealth - heroesDamage[i] * coef;
                            }
                        }
                        System.out.println(heroesAttackType[i] +
                                " critically hit " + heroesDamage[i] * coef);
                    }

                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    private static int medHeroesHealth() {
        int medIndex = 0;
        int minValue = heroesHealth[0];
        for (int i = 1; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > minValue && heroesHealth[i]<100) {
                minValue = heroesHealth[i];
                medIndex = i;
                System.out.println();
            }
        }
        return medIndex;
    }
    public static void healHeroues(){
        Random random = new Random();
        int a =random.nextInt(2);
        if (heroesHealth[3] > 0) {
            heroesHealth[a] = heroesHealth[a] + 20;
            System.out.println("Врач вылечил " + a + " на 20");
        }else{
            System.out.println("врач умер");
        }
    }


    public static void printStatistic() {
        System.out.println("------------------");
        System.out.println("Boss health " + bossHealth);
        System.out.println("Warrior health " + heroesHealth[0]);
        System.out.println("Magic health " + heroesHealth[1]);
        System.out.println("Kinetik health " + heroesHealth[2]);
        System.out.println("Medic health " + heroesHealth[3]);
        System.out.println("-------------------");
    }


}