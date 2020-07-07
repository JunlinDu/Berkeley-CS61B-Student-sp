public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        LinkedListDeque<Character> deque = new LinkedListDeque<>();
        for(int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    private boolean isPalindromeHelper(Deque<Character> deque) {
        if(deque.size() == 0 || deque.size() == 1){
            return true;
        }
        Character first = deque.removeFirst();
        Character last = deque.removeLast();
        if(first == last) {
            return isPalindromeHelper(deque);
        }
        return false;
    }

    public boolean isPalindrome(String word) {
        return isPalindromeHelper(wordToDeque(word));
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        return true;
    }

    public boolean isPalindromeNoDeque(String word) {
        if (word.length() == 0 || word.length() ==1) {
            return true;
        }

        for(int i = 0; i < word.length()/2; i++){
            if(word.charAt(i) != word.charAt(word.length()-i-1)){
                return false;
            }
        }
        return true;
    }
}
