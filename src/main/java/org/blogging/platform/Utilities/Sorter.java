package org.blogging.platform.Utilities;

import org.blogging.platform.Models.Post;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sorter {
    static class MergeSort {
        static List<Post> sort(List<Post> posts, Comparator<Post> comparator) {
            if (posts.size() <= 1) return new ArrayList<>(posts);
            int middle = posts.size() / 2;
            List<Post> left = sort(posts.subList(0, middle), comparator);
            List<Post> right = sort(posts.subList(middle, posts.size()), comparator);
            return merge(left, right, comparator);
        }
        private static List<Post> merge(List<Post> left, List<Post> right, Comparator<Post> comparator) {
            List<Post> merged = new ArrayList<>();
            int leftIndex = 0, rightIndex = 0;
            while (leftIndex < left.size() && rightIndex < right.size()) {
                if (comparator.compare(left.get(leftIndex), right.get(rightIndex)) <= 0) {
                    merged.add(left.get(leftIndex++));
                } else {
                    merged.add(right.get(rightIndex++));
                }
            }
            merged.addAll(left.subList(leftIndex, left.size()));
            merged.addAll(right.subList(rightIndex, right.size()));
            return merged;
        }

    }
    static public List<Post> sort(List<Post> posts, String by) {
        return switch (by) {
            case "A-Z" -> MergeSort.sort(posts, Comparator.comparing(post -> post.Title.toLowerCase()));
            case "Latest" -> {
                Comparator<Post> comparator = Comparator.comparing(post -> post.CreatedAt);
                yield MergeSort.sort(posts, comparator.reversed());
            }
            case "By Author" -> MergeSort.sort(posts, Comparator.comparing(post -> post.Username.toLowerCase()));
            default -> throw new IllegalArgumentException("Unsupported sort criteria: " + by);
        };
    }
}
