package com.codermartai.pool;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> strings = solution.generateParenthesis(4);
        System.out.println("n为4的结果："+strings);
    }
}

class Solution {
    int t = 0;
    public List<String> generateParenthesis(int n) {
        List<String> dpList = new ArrayList();
        dpList.add("()");

        List<String> newList = new ArrayList();

        for(int i = 0;i<n-1;i++){
            addFirstAndList(dpList,newList);
            addLeftAndR(i+2,dpList,newList);

            //重新划归新旧列表
            dpList = newList;
            newList = new ArrayList();
        }
        return dpList;
    }

    //添加到首尾
    public void addFirstAndList(List<String> dpList,List<String> newList){
        for(int i = 0;i<dpList.size();i++){
            StringBuffer str = new StringBuffer("(");
            str.append(dpList.get(i));
            str.append(")");
            newList.add(str.toString());
        }
    }

    //添加到左右
    public void addLeftAndR(int n,List<String> dpList,List<String> newList){
        int dpLen = dpList.size();
        StringBuffer str = new StringBuffer();
        for(int i = 0;i<n;i++){
            str.append("()");
        }

        //添加到左
        for(int i = 0;i<dpLen;i++){
            StringBuffer lStr = new StringBuffer("()");
            lStr.append(dpList.get(i));
            newList.add(lStr.toString());
        }

        //添加到右
        for(int i = 0;i<dpLen;i++){
            StringBuffer RStr = new StringBuffer();
            RStr.append(dpList.get(i));
            RStr.append("()");
            newList.add(RStr.toString());
        }

        int lastIndex = newList.lastIndexOf(str.toString());
        if(lastIndex != -1){
            t++;
            System.out.println("这是第"+t+"次删除");
            System.out.println("当前n为："+newList.size()+"-已经删除一个"+str.toString());
            newList.remove(lastIndex);
            System.out.println("-"+newList);
        }
    }
}

