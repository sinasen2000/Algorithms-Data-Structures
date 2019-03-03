package Recommendation;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * This recommendation algorithm makes use of it is the simplest form of non-trivial item-based collaborative filtering based on ratings.
 * Its simplicity makes it especially easy to implement it efficiently while its accuracy is often on par with more complicated and computationally expensive algorithms.
 * Note: The description is taken from Wikipedia
 */
public class SlopeOne {


        public static void main(String args[]){

            // a map to store all data
            Map<String, Map<String, Double>> data = new HashMap<>();


            // items
            String item1 = "Running";
            String item2 = "Biking";
            String item3 = "Eating vegetarian";
            String item4 = "Recycling";
            String item5 = "Using public transport";
            String item6 = "Buying local products";
            String item7 = "Using solar panels";
            String item8 = "Supporting clean energy sources";


            System.out.println("Welcome to the activity recommendation tool!\n");
            System.out.println("Please write your name: ");
            Scanner sc = new Scanner(System.in);
            String username = sc.next();



            allItems = new String[]{item1, item2, item3, item4, item5, item6, item7, item8};

            // user and his preferences

            HashMap<String, Double> user1 = new HashMap<>();
            HashMap<String, Double> user2 = new HashMap<>();
            HashMap<String, Double> user3 = new HashMap<>();
            HashMap<String, Double> user4 = new HashMap<>();


            user1.put(item1, 0.6);
            user1.put(item2, 0.8);
            user1.put(item3, 0.5);
            user1.put(item4, 1.0);
            user1.put(item5, 0.3);
            user1.put(item6, 0.7);
            user1.put(item7, 0.7);
            user1.put(item8, 0.4);
            data.put("", user1);
            user2.put(item1, 0.7);
            user2.put(item2, 0.3);
            user2.put(item3, 0.9);
            user2.put(item4, 0.4);
            user2.put(item5, 0.7);
            user2.put(item6, 0.9);
            user2.put(item7, 0.3);
            user2.put(item8, 0.6);
            data.put("", user2);
            user3.put(item1, 0.2);
            user3.put(item2, 0.4);
            user3.put(item3, 0.8);
            user3.put(item4, 0.6);
            user3.put(item5, 0.3);
            user3.put(item6, 0.1);
            user3.put(item7, 0.8);
            user3.put(item8, 0.5);
            data.put("", user3);
            user4.put(item1, 0.1);
            user4.put(item2, 0.7);
            user4.put(item3, 0.4);
            user4.put(item4, 0.7);
            user4.put(item5, 0.2);
            user4.put(item6, 0.8);
            user4.put(item7, 0.9);
            user4.put(item8, 0.6);
            data.put("", user4);


            // generating the predictor
            SlopeOne s1 = new SlopeOne(data);
            System.out.println("Here's the average interest (out of 1.0 which 1.0 means highly interested): \n");
            s1.printData();

            HashMap<String, Double> user = new HashMap<>();
            System.out.println("Now it's time for some recommendations!!\n");
            System.out.println("Please rate the following activities that you would like to do in order to " +
                    " reduce your carbon footprint\n" +
                    "0 means no interest, 0.5 means somehow interested, 1.0 means really interested.\n ");
            System.out.println("The output will show you which activities  that you can focus on in order to" +
                    " reduce your carbon footprint: \n");
            System.out.println("1- Running: ");
            Double rate1 = sc.nextDouble();
            user.put(item1, rate1);
            System.out.println("Inputting...");
            SlopeOne.print(user);
            System.out.println("Getting...");
            SlopeOne.print(s1.predict(user));
            System.out.println("2- Biking: ");
            Double rate2 = sc.nextDouble();
            user.put(item2, rate2);
            System.out.println("Inputting...");
            SlopeOne.print(user);
            System.out.println("Getting...");
            SlopeOne.print(s1.predict(user));
            System.out.println("3- Eating vegetarian: ");
            Double rate3 = sc.nextDouble();
            user.put(item3, rate3);
            System.out.println("Inputting...");
            SlopeOne.print(user);
            System.out.println("Getting...");
            SlopeOne.print(s1.predict(user));
            System.out.println("4- Recycling: ");
            Double rate4 = sc.nextDouble();
            user.put(item4, rate4);
            System.out.println("Inputting...");
            SlopeOne.print(user);
            System.out.println("Getting...");
            SlopeOne.print(s1.predict(user));
            System.out.println("5- Using public transport: ");
            Double rate5 = sc.nextDouble();
            user.put(item5, rate5);
            System.out.println("Inputting...");
            SlopeOne.print(user);
            System.out.println("Getting...");
            SlopeOne.print(s1.predict(user));
            System.out.println("6- Buying local products: ");
            Double rate6 = sc.nextDouble();
            user.put(item6, rate6);
            System.out.println("Inputting...");
            SlopeOne.print(user);
            System.out.println("Getting...");
            SlopeOne.print(s1.predict(user));
            System.out.println("7- Using solar panels: ");
            Double rate7 = sc.nextDouble();
            user.put(item7, rate7);
            System.out.println("Inputting...");
            SlopeOne.print(user);
            System.out.println("Getting...");
            SlopeOne.print(s1.predict(user));
            System.out.println("8- Supporting clean energy sources: ");
            Double rate8 = sc.nextDouble();
            user.put(item8, rate8);
            System.out.println("Inputting...");
            SlopeOne.print(user);
            System.out.println("Getting...");
            SlopeOne.print(s1.predict(user));



        }

        Map<String, Map<String, Double>> mData;
        Map<String, Map<String, Double>> diffMatrix;
        Map<String, Map<String, Integer>> freqMatrix;

        static String[]                    allItems;

        public SlopeOne(Map<String, Map<String, Double>> data){
            mData = data;
            buildDiffMatrix();
        }



        public Map<String, Double> predict(Map<String, Double> user) {
            HashMap<String, Double> predictions = new HashMap<>();
            HashMap<String, Integer> frequencies = new HashMap<>();

            for (String j : diffMatrix.keySet()) {
                frequencies.put(j, 0);
                predictions.put(j, 0.0);
            }
            for (String j : user.keySet()) {
                for (String k : diffMatrix.keySet()) {
                    try {
                        Double newValue = (diffMatrix.get(k).get(j) + user.get(j)) * freqMatrix.get(k).get(j).intValue();
                        predictions.put(k, predictions.get(k) + newValue);
                        frequencies.put(k, frequencies.get(k) + freqMatrix.get(k).get(j).intValue());

                    } catch (NullPointerException e) {
                    }

                }
            }
            HashMap<String, Double> cleanPredictions = new HashMap<>();
            for (String j : predictions.keySet()){
                if(frequencies.get(j) > 0){
                    cleanPredictions.put(j, predictions.get(j) / frequencies.get(j).intValue());
                }
            }
            for (String j : user.keySet()){
                cleanPredictions.put(j, user.get(j));
            }
            return cleanPredictions;
        }

        public Map<String, Double> weightlessPredict(Map<String, Double> user){

            HashMap<String, Double> predictions = new HashMap<>();
            HashMap<String, Integer> frequencies = new HashMap<>();

            for (String j : diffMatrix.keySet()){
                frequencies.put(j, 0);
                predictions.put(j, 0.0);
            }
            for (String j : user.keySet()){
                for (String k : diffMatrix.keySet()){
                    Double newValue = (diffMatrix.get(k).get(j) + user.get(j));
                    predictions.put(k, predictions.get(k) + newValue);
                }
            }
            for (String j : predictions.keySet()){
                predictions.put(j, predictions.get(j) / user.size());
            }
            for (String j : user.keySet()){
                predictions.put(j, user.get(j));
            }
            return predictions;
        }




        public void buildDiffMatrix(){
            diffMatrix = new HashMap<>();
            freqMatrix = new HashMap<>();

            // first iterate through users
            for (Map<String, Double> user : mData.values()){
                // then iterate through user data
                for (Map.Entry<String, Double> entry : user.entrySet()){
                    String i1 = entry.getKey();
                    double r1 = entry.getValue();

                    if(!diffMatrix.containsKey(i1)){
                        diffMatrix.put(i1, new HashMap<String, Double>());
                        freqMatrix.put(i1, new HashMap<String, Integer>());
                    }
                    for (Map.Entry<String, Double> entry2 : user.entrySet()){
                        String i2 = entry2.getKey();
                        Double r2 = entry2.getValue();

                        int count = 0;
                        if(freqMatrix.get(i1).containsKey(i2)) count = freqMatrix.get(i1).get(i2);
                        double diff = 0.0;
                        if(diffMatrix.get(i1).containsKey(i2)) diff = diffMatrix.get(i1).get(i2);
                        double newDiff = r1 - r2;

                        freqMatrix.get(i1).put(i2, count);
                        diffMatrix.get(i1).put(i2, diff + newDiff);
                    }
                }
            }
            for (String j : diffMatrix.keySet()){
                for (String i : diffMatrix.get(j).keySet()){
                    Double oldvalue = diffMatrix.get(j).get(i);
                    int count = freqMatrix.get(j).get(i).intValue();
                    diffMatrix.get(j).put(i, oldvalue / count);
                }
            }
        }
        public static void print(Map<String, Double> user){
            for (String j : user.keySet()){
                System.out.println(" " + j + " --> " + user.get(j).floatValue());
            }
        }
        private void printMatrixes(Map<String, Double> ratings, Map<String, Integer> frequencies){
            for (int j = 0; j < allItems.length; j++){
                System.out.format("%10.3f", ratings.get(allItems[j]));
                System.out.print(" ");
                System.out.format("%10d", frequencies.get(allItems[j]));
            }
            System.out.println();
        }
        public void printData(){
            for (String user : mData.keySet()){
                System.out.println(user);
                print(mData.get(user));
            }
            for (int i = 0; i < allItems.length; i++){
                System.out.println("\n" + allItems[i] + ": ");
                printMatrixes(diffMatrix.get(allItems[i]), freqMatrix.get(allItems[i]));
            }
        }
    }

