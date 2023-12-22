package com.lead.dashboard.controller.leadController;

public class TestPractice {
	 static long inversionCount(long arr[], long N) {
	        long[] temp = new long[(int)N];
	        return mergeSortAndCount(arr, temp, 0, (int)(N - 1));
	    }
	    
	    static long mergeSortAndCount(long arr[], long temp[], int left, int right) {
	        long count = 0;
	        if (left < right) {
	            int mid = (left + right) / 2;
	            count += mergeSortAndCount(arr, temp, left, mid);
	            count += mergeSortAndCount(arr, temp, mid + 1, right);
	            count += merge(arr, temp, left, mid, right);
	        }
	        return count;
	    }

	    static long merge(long arr[], long temp[], int left, int mid, int right) {
	        int i = left;
	        int j = mid + 1;
	        int k = left;
	        long count = 0;

	        while (i <= mid && j <= right) {
	            if (arr[i] <= arr[j]) {
	                temp[k++] = arr[i++];
	            } else {
	                temp[k++] = arr[j++];
	                count += (mid - i + 1);
	            }
	        }

	        while (i <= mid) {
	            temp[k++] = arr[i++];
	        }

	        while (j <= right) {
	            temp[k++] = arr[j++];
	        }

	        for (i = left; i <= right; i++) {
	            arr[i] = temp[i];
	        }

	        return count;
	    }
}
