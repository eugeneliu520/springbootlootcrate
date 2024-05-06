package com.eugene.springboot.lootcrate.utils;

/**
 * @author eugeneliu
 * @ClassName ClassNameMatchUtils
 * @Description TODO
 * @Date 2023/1/29 4:10 PM
 **/
public class ClassNameMatchUtils {
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];//初始化
        dp[0][0] = true;
        for (int j = 1; j <= n; j++) {
            char c = p.charAt(j - 1);
            if (c == '*') {
                dp[0][j] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char si = s.charAt(i - 1);
                char pj = p.charAt(j - 1);
                if (pj == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else {
                    dp[i][j] = (pj == '?' || si == pj) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }
}
