package com.cis.Utils;

import com.cis.Model.Data;
import com.cis.Model.Post;

import java.util.Comparator;

public class SortPostByTime implements Comparator<String> {
    @Override
    public int compare(String postUID1, String postUID2) {
        Post post1 = Data.getInstance().getPosts().get(postUID1);
        Post post2 = Data.getInstance().getPosts().get(postUID2);

        int result = (int) (post2.getCreationDate() - post1.getCreationDate());

        if (result == 0) {
            return 1;
        }
        return result;
    }
}
