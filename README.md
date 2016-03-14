# Anagrams


##Find anagrams from dictionary


##Description
 My approach is first sort a single word, for example, the word "hello" will become "ehllo".
 Then I sort the whole list of words. At then end, if some words are anagrams, they must have
 the same sorted form and they must be next to each other.
 
 I use tuple to store the original form and sorted form of a word, as you can see in class Tuple.
 The first String is the original form and second is the sorted form.
 
 I use counting sort in the book to sort the word, it runs in linear time and the range K is only 26.
 After I sorted every word, make them into tuples and store them into list. 
 I sorted the whole list using merge sort which runs in O(nlogn) time, I compare the sorted form to
 sort the list.
 
 Finally I go through the list and write the anagram groups out into files.
 
 
 ##Correctness
 My method must be correct because if two words are anagrams, then they must have the same sorted form
 For example, "paradisiacally" and "paradisaically", they both have the same sorted form "aaaacdiillprsy".
 
 
 ##Runtime
 Counting-Sort runs in linear time which is O(n+26).
 Merge-Sort runs in O(nlogn) time.
 and writing into files takes O(n) time.
 So the final run time is O(nlogn)
 
 
 
 
 
