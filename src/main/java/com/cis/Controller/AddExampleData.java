package com.cis.Controller;

import com.cis.Model.*;

public class AddExampleData {

    public static void addData() {

        // Adds test data to the server

        Data data = Data.getInstance();

        System.out.println("Adding Example Data");

        data.addUser(new User("c28e6c3e-d720-482b-8062-ebb82798c600",
                              "leafuwu",
                              "Maia K",
                              "I love trees!",
                              "https://i.imgur.com/0loCM3K.jpg"));

        data.addUser(new User("9c01220d-4acb-4282-b0b7-306fb2b9f9b1",
                              "grassguy",
                              "Gerald Smithers",
                              "Hope you guys like my account!",
                              "https://i.imgur.com/N4jhbXl.jpg"));

        data.addUser(new User("d2db228e-5d9d-4479-b81a-a9521ff5c63e",
                              "treebu",
                              "Nick",
                              "I post photos!",
                              "https://i.imgur.com/hLtIC3a.jpg"));

        data.addUser(new User("e2c61608-1d45-4eff-a052-23d688e30c8b",
                              "croissantdream",
                              "Loren",
                              "I'm loving Canopy!!",
                              "https://i.imgur.com/8yyqjX0.jpg"));

        data.addUser(new User("88a57326-3fd8-4adf-ae66-5e24a3561b44",
                              "tisaclam",
                              "Issa",
                              "Love trees like I love math",
                              "https://i.imgur.com/SuQe1dW.jpg"));

        data.addUser(new User("46d4b4e5-afed-42eb-87df-0208f0fbb113",
                              "OnlyJS",
                              "Julia",
                              "Swift is life!",
                              "https://i.imgur.com/Oq6uwSK.jpg"));

        data.addUser(new User("8caff95c-1112-4e85-914f-a9b94d3d267b",
                              "eggbro",
                              "Brody",
                              "This app is so cool!",
                              "https://i.imgur.com/HuQnH5B.jpg"));

        data.addPost(new Post("61bede89-8de3-42d4-9cc8-351f57ded067",
                              "c28e6c3e-d720-482b-8062-ebb82798c600",
                              "https://i.imgur.com/O7rGk72.jpg",
                              "A cool tree I saw in the park!",
                              (System.currentTimeMillis() * 0.001) - 10000));

        data.addPost(new Post("f5cc5eb5-8049-47c6-89f8-2522878c8d21",
                              "d2db228e-5d9d-4479-b81a-a9521ff5c63e",
                              "https://i.imgur.com/2Ajto84.jpg",
                              "My favourite picture from when I saw Rhinos on the safari! They are critically " +
                                      "endangered, help raise awareness!",
                              (System.currentTimeMillis() * 0.001) - 10000));

        data.addPost(new Post("a1fd730e-0b87-451a-8163-602050920f42",
                              "9c01220d-4acb-4282-b0b7-306fb2b9f9b1",
                              "https://i.imgur.com/EvyTu8g.jpg",
                              "Save the trees! This beautiful environment is ours to protect!",
                              (System.currentTimeMillis() * 0.001) - 10000));

        data.addPost(new Post("ccf509b2-b26a-4f75-bddc-9e7946e33ae1",
                              "d2db228e-5d9d-4479-b81a-a9521ff5c63e",
                              "https://i.imgur.com/h01eqLK.jpg",
                              "Amazing! Spend a day cleaning up the park!",
                              (System.currentTimeMillis() * 0.001) - 10000));

        data.addPost(new Post("8eddb1f2-ca65-49c7-b606-81eaef2fe8b1",
                              "c28e6c3e-d720-482b-8062-ebb82798c600",
                              "https://i.imgur.com/Wzm1H5Q.jpg",
                              "People litter the weirdest things! #StopLittering",
                              (System.currentTimeMillis() * 0.001) - 10000));

        data.addPost(new Post("ce7c9791-345a-4ac0-96c4-2a961db6f772",
                              "d2db228e-5d9d-4479-b81a-a9521ff5c63e",
                              "https://i.imgur.com/O8n6mRe.jpg",
                              "This place is so pretty! Wish we had more places where there are trees and nature",
                              (System.currentTimeMillis() * 0.001) - 10000));

        data.addPost(new Post("20ae1c0a-89ee-4baa-894d-cec51891f2e8",
                              "9c01220d-4acb-4282-b0b7-306fb2b9f9b1",
                              "https://i.imgur.com/SYdO76t.jpg",
                              "So serene! If we keep polluting our waters we won't have places like this anymore!!",
                              (System.currentTimeMillis() * 0.001) - 10000));

        data.addPost(new Post("87956988-c452-4493-9c0e-fd658cd3eb84",
                              "c28e6c3e-d720-482b-8062-ebb82798c600",
                              "https://i.imgur.com/DMBzDsm.jpg",
                              "Been giving my old toys to charity #ReduceReuseRecycle",
                              (System.currentTimeMillis() * 0.001) - 10000));

        data.addPost(new Post("4a35c2e6-089d-4d2e-83ed-86c57cea5b67",
                              "9c01220d-4acb-4282-b0b7-306fb2b9f9b1",
                              "https://i.imgur.com/cpeoAgV.jpg",
                              "Starting the Sakura Project! Planting beautiful Sakura trees all over Japan to keep " +
                                      "things beautiful and greener!",
                              (System.currentTimeMillis() * 0.001) - 10000));

        data.addComment(new Comment("f6d04991-deb7-4e43-8741-b86140774513",
                                    "e2c61608-1d45-4eff-a052-23d688e30c8b",
                                    "61bede89-8de3-42d4-9cc8-351f57ded067",
                                    "Wow great post",
                                    (System.currentTimeMillis() * 0.001) - 500));

        data.addComment(new Comment("7a3af137-50a3-40f5-aeb9-4eceb16f4998",
                                    "c28e6c3e-d720-482b-8062-ebb82798c600",
                                    "f5cc5eb5-8049-47c6-89f8-2522878c8d21",
                                    "This post is great!",
                                    (System.currentTimeMillis() * 0.001) - 500));

        data.addComment(new Comment("98e2318e-fa6e-457c-99da-ce8a5d9f470e",
                                    "8caff95c-1112-4e85-914f-a9b94d3d267b",
                                    "a1fd730e-0b87-451a-8163-602050920f42",
                                    "WOW!!!",
                                    (System.currentTimeMillis() * 0.001) - 500));

        data.addComment(new Comment("168fc86d-8bce-41dc-89b6-f21f0434a02e",
                                    "e2c61608-1d45-4eff-a052-23d688e30c8b",
                                    "ccf509b2-b26a-4f75-bddc-9e7946e33ae1",
                                    "Great!",
                                    (System.currentTimeMillis() * 0.001) - 500));

        data.addComment(new Comment("f6d04991-deb7-4e43-8741-b86140774513",
                                    "46d4b4e5-afed-42eb-87df-0208f0fbb113",
                                    "61bede89-8de3-42d4-9cc8-351f57ded067",
                                    "This is so insightful thanks!",
                                    (System.currentTimeMillis() * 0.001) - 500));

        data.addComment(new Comment("7a3af137-50a3-40f5-aeb9-4eceb16f4998",
                                    "46d4b4e5-afed-42eb-87df-0208f0fbb113",
                                    "f5cc5eb5-8049-47c6-89f8-2522878c8d21",
                                    "You are the coolest",
                                    (System.currentTimeMillis() * 0.001) - 500));

        data.addComment(new Comment("98e2318e-fa6e-457c-99da-ce8a5d9f470e",
                                    "88a57326-3fd8-4adf-ae66-5e24a3561b44",
                                    "a1fd730e-0b87-451a-8163-602050920f42",
                                    "That's epic bro",
                                    (System.currentTimeMillis() * 0.001) - 500));

        data.addComment(new Comment("168fc86d-8bce-41dc-89b6-f21f0434a02e",
                                    "88a57326-3fd8-4adf-ae66-5e24a3561b44",
                                    "ccf509b2-b26a-4f75-bddc-9e7946e33ae1",
                                    "This is the kind of content I've been looking for",
                                    (System.currentTimeMillis() * 0.001) - 500));

        data.addDiscount(new Discount("c234a6a8-98ff-41a1-b156-c9fcebaed705",
                                      100,
                                      "Greendotdot",
                                      "25% off 5 Grain Rice",
                                      "Get 25% off 5 Grain Rice with 100 points!"));

        data.addDiscount(new Discount("e07787ba-4715-4296-93f0-54e21775f072",
                                      100,
                                      "Greendotdot",
                                      "25% off Cashew and Walnut Mixed Powder",
                                      "Get 25% off Cashew and Walnut Mixed Powder with 100 points!"));

        data.addDiscount(new Discount("21619653-9f64-42ec-a425-257d2669dd33",
                                      200,
                                      "Greendotdot",
                                      "15% off Wild Honey",
                                      "Get 15% off Wild Honey with 200 points!"));

        data.addDiscount(new Discount("befde7be-8d7b-4150-a0ba-05f24be3fef2",
                                      1000,
                                      "Greendotdot",
                                      "Buy 1 Get 1 Free Coupon",
                                      "Buy 1 Get 1 Free for any item with 1000 points!"));

        data.addDiscount(new Discount("ab2e7def-c88f-4e55-b40a-73d96a2dcb84",
                                      250,
                                      "John Masters Organics",
                                      "50% off Rosemary Shampoo",
                                      "Get 50% off Shampoo For Fine Hair With Rosemary And Peppermint " +
                                              "with 250 points!"));

        data.addDiscount(new Discount("7022c31a-3181-42dd-a269-3be3ce1753f7",
                                      150,
                                      "John Masters Organics",
                                      "25% off Raspberry Hand Cream",
                                      "Get 25% off Raspberry Hand Cream with 150 points!"));

        data.addDiscount(new Discount("a32a93f4-db51-4242-8705-c3e43948a105",
                                      100,
                                      "John Masters Organics",
                                      "10% off Lemongrass Body Lotion",
                                      "Get 10% off Fresh Lemongrass Body Lotion for 100 points!"));

        data.addDiscount(new Discount("755ac301-15c3-4e32-ac9f-6b04a818823d",
                                      250,
                                      "John Masters Organics",
                                      "Free Cucumber and Lime Face Wash",
                                      "Get one free 100 ml Organic Cucumber and Lime Face Wash for 250 points"));

        data.addChallenge(new Challenge("8b3fcaf1-7bbb-4e6c-b029-b1ca17e16b2d",
                                        "Hug a Tree!",
                                        "Take a picture of yourself hugging a tree! Add a description about " +
                                                "the type of tree and some of the cool things it does for us! " +
                                                "Best picture gets 200 points",
                                        200,
                                        (System.currentTimeMillis() * 0.001) + 86400.0,
                                        "https://i.imgur.com/9SQwgp4.jpg"));

        data.addChallenge(new Challenge("63f8a063-92b2-4caa-8381-a349fcffdba6",
                                        "Design Challenge",
                                        "Take a picture and write about an invention that helps you " +
                                                "solve an environmental issue!",
                                        1000,
                                        (System.currentTimeMillis() * 0.001) + 604800.0,
                                        "https://i.imgur.com/BxZfNUO.jpg"));

        data.addChallenge(new Challenge("2f4629b7-d793-42d7-8367-930cc5d9d981",
                                        "Beach Cleanup",
                                        "Take a picture of yourself at a beach cleanup! " +
                                                "Talk about ways littering harms the environment",
                                        500,
                                        (System.currentTimeMillis() * 0.001) + 604800.0,
                                        "https://i.imgur.com/k0jlXwa.jpg"));
    }
}
