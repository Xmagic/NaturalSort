import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NaturalSortTester {

	public static void main(String[] args) {
		new NaturalSortTester();
	}
	
	String[] arr = new String[]{"txt1", "txt2", "txt10", "txt20", "txt11", "txt22", "txt100"};
	
	public NaturalSortTester (){
		
		List<String> list = new ArrayList<String>(Arrays.asList(arr));//.asList(arr);
		Collections.sort(list, naturalSortComparator());
		System.out.println(list);
	}
	
	private static final Pattern PATTERN = Pattern.compile("(\\D*)(\\d*)");


	public Comparator<CharSequence> naturalSortComparator() {
		return new Comparator<CharSequence>() {

			@Override
			public int compare(CharSequence s1, CharSequence s2) {
		        Matcher m1 = PATTERN.matcher(s1);
		        Matcher m2 = PATTERN.matcher(s2);

		        // The only way find() could fail is at the end of a string
		        while (m1.find() && m2.find()) {
		            // matcher.group(1) fetches any non-digits captured by the
		            // first parentheses in PATTERN.
		            int nonDigitCompare = m1.group(1).compareTo(m2.group(1));
		            if (0 != nonDigitCompare) {
		                return nonDigitCompare;
		            }

		            // matcher.group(2) fetches any digits captured by the
		            // second parentheses in PATTERN.
		            if (m1.group(2).isEmpty()) {
		                return m2.group(2).isEmpty() ? 0 : -1;
		            } else if (m2.group(2).isEmpty()) {
		                return +1;
		            }

		            BigInteger n1 = new BigInteger(m1.group(2));
		            BigInteger n2 = new BigInteger(m2.group(2));
		            int numberCompare = n1.compareTo(n2);
		            if (0 != numberCompare) {
		                return numberCompare;
		            }
		        }

		        // Handle if one string is a prefix of the other.
		        // Nothing comes before something.
		        return m1.hitEnd() && m2.hitEnd() ? 0 :
		               m1.hitEnd()                ? -1 : +1;
		    }
//			public int compare(String o1, String o2) {
//				String[] s1Parts = o1.split("");
//				String[] s2Parts = o2.split("");
//				for (int i = 0; i < s1Parts.length && i < s2Parts.length; ++i) {
//				    //if parts are the same
//				    if (s1Parts[i].compareTo(s2Parts[i]) == 0) {
//				        continue;
//				    }
//				    try {
//				        int intS1 = Integer.parseInt(s1Parts[i]);
//				        int intS2 = Integer.parseInt(s2Parts[i]);
//
//				        //if the parse works
//				        int diff = intS1 - intS2; 
//				        if (diff == 0) {
//				            // continue;    // Actually, this is a no-op
//				        } else {
//				            return diff;
//				        }
//				    } catch (NumberFormatException ex) {
//				        // Buggy, as noted by @rolfl
//				        // return s1.compareTo(s2);
//				        return s1Parts[i].compareTo(s2Parts[i]);
//				    }
//				}
//				return o1.length() - o2.length();
//			}
		};
		
	}

}
