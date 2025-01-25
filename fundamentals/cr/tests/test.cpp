#include "pch.h"
#include <string>
#include "../controlnay/functions.cpp"

TEST(SequenceLength,RightTests) {
    int TestData;
    TestData = 1;
  EXPECT_EQ(SequenceLength(TestData) , 1);
     TestData = 0;
  EXPECT_EQ(SequenceLength(TestData), 0);
    TestData = 5;
  EXPECT_EQ(SequenceLength(TestData), 16);
    TestData = 3;
  EXPECT_EQ(SequenceLength(TestData), 4);
    TestData = 8;
  EXPECT_EQ(SequenceLength(TestData), 128);
    TestData = 11;
  EXPECT_EQ(SequenceLength(TestData), 1024);
    TestData = 7;
  EXPECT_EQ(SequenceLength(TestData), 64);
    TestData = 2;
  EXPECT_EQ(SequenceLength(TestData), 2);
    TestData = 4;
  EXPECT_TRUE(SequenceLength(TestData) == 8);
  TestData = 6;
  EXPECT_FALSE(SequenceLength(TestData) == 6);
}


TEST(NumberOfOnesBinaryNotation, RightTests) {
    int TestData;
    TestData = 1;
    EXPECT_EQ(NumberOfOnesBinaryNotation(TestData), 1);
    TestData = 0;
    EXPECT_EQ(NumberOfOnesBinaryNotation(TestData), 0);
    TestData = 5;
    EXPECT_EQ(NumberOfOnesBinaryNotation(TestData), 2);
    TestData = 3;
    EXPECT_EQ(NumberOfOnesBinaryNotation(TestData), 2);
    TestData = 8;
    EXPECT_EQ(NumberOfOnesBinaryNotation(TestData), 1);
    TestData = 11;
    EXPECT_EQ(NumberOfOnesBinaryNotation(TestData), 3);
    TestData = 7;
    EXPECT_EQ(NumberOfOnesBinaryNotation(TestData), 3);
    TestData = 2;
    EXPECT_EQ(NumberOfOnesBinaryNotation(TestData), 1);
    TestData = 4;
    EXPECT_TRUE(NumberOfOnesBinaryNotation(TestData) == 1);
    TestData = 6;
    EXPECT_FALSE(NumberOfOnesBinaryNotation(TestData) == 3);
}

