package webone.profile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class SearchController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private ProfileManager pm;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		try {
			String term = request.getParameter("term");
			System.out.println("Data from ajax call " + term);

			Set<String> list = filterOutLoginList(pm.getAllLogins(), term);

			String searchList = new Gson().toJson(list);
			response.getWriter().write(searchList);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * This function prepares list of suggested logins (when searching other
	 * profiles)
	 * 
	 * @param listToFilterOut
	 *            - list of all logins
	 * @param query
	 *            - characters already typed by user
	 * @return filtered list of logins
	 */
	private Set<String> filterOutLoginList(List<String> listToFilterOut, String query) {

		query = query.toLowerCase();
		
		final int MAX_RECORDS = 10;
		final int MAX_PERMUTATIONS = 100000;

		Set<String> permutations = null;
		Set<String> permutations2 = new LinkedHashSet<String>();;
		Set<String> result = new LinkedHashSet<String>();

		

		/**********************
		 * FIRST PERMUTATION
		 **********************/

		System.out.println("Start first permutations");
		permutations = doPermutations(query);
		System.out.println("First permutations done, n: "+permutations.size());
			
		System.out.println("ORGINAL");

		// ORGINAL
		// if any login starts current typed word
		for (String login : listToFilterOut) {
			if (login.toLowerCase().startsWith(query)) {
				if (!result.contains(login))
					result.add(login);
				if (result.size() >= MAX_RECORDS)
					return result;
			}
		}

//		System.out.println("FIRST PERMUTATION EQUALS");
//		
//		// FIRST PERMUTATION EQUALS
//		// if any login starts with or equals any permutation
//		for (String permutation : permutations) {
//			for (String login : listToFilterOut) {
//				if (login.toLowerCase().equals(permutation)) {
//					if (!result.contains(login))
//						result.add(login);
//					if (result.size() >= MAX_RECORDS)
//						return result;
//				}
//			}
//		}
		
		System.out.println("FIRST PERMUTATION STARTWS WITH");

		// FIRST PERMUTATION STARTS WITH
		// if any login starts with or equals any permutation
		for (String permutation : permutations) {
			for (String login : listToFilterOut) {
				if (login.toLowerCase().startsWith(permutation)) {
					if (!result.contains(login))
						result.add(login);
					if (result.size() >= MAX_RECORDS)
						return result;
				}
			}
		}
		
		
		/**********************
		 * SECOND PERMUTATION
		 **********************/
		
		if (listToFilterOut.size() > 10000)
			return result;
		
		//second permutation is performed if user typed at least two characters
		if (query.length() > 1) {
			// second permutation (permutation on permutations)
			System.out.println("Start second permutations");
			for (String permutation : permutations) {
				permutations2.addAll(doPermutations(permutation));
				if (permutations2.size() > MAX_PERMUTATIONS)
					break;
			}
			System.out.println("Second permutations done, n: "+permutations2.size());
		}
		
//		System.out.println("SECOND PERMUTATION EQUALS");
//
//		// SECOND PERMUTATION EQUALS
//		// if any login starts with or equals any permutations2
//		for (String permutation : permutations2) {
//			for (String login : listToFilterOut) {
//				if (login.toLowerCase().equals(permutation)) {
//					if (!result.contains(login))
//						result.add(login);
//					if (result.size() >= MAX_RECORDS)
//						return result;
//				}
//			}
//		}
		
		System.out.println("SECOND PERMUTATION STARTS WITH");

		// SECOND PERMUTATION STARTS WITH
		// if any login starts with or equals any permutations2
		for (String permutation : permutations2) {
			for (String login : listToFilterOut) {
				if (login.toLowerCase().startsWith(permutation)) {
					if (!result.contains(login))
						result.add(login);
					if (result.size() >= MAX_RECORDS)
						return result;
				}
			}
		}

		System.out.println("Suggestions: " + result.toString());

		return result;
	}

	private Set<String> doPermutations(String word) {
		Set<String> permutations = new LinkedHashSet<>();

		permutations.add(word);
		
		// Swap two consecutive characters
		for (int i = 0; i < word.length() - 1; ++i)
			permutations.add(word.substring(0, i) + word.substring(i + 1, i + 2) + word.substring(i, i + 1)
					+ word.substring(i + 2));
		// Replace a character with other
		for (int i = 0; i < word.length(); ++i)
			for (char c = 'a'; c <= 'z'; ++c)
				permutations.add(word.substring(0, i) + String.valueOf(c) + word.substring(i + 1));
		// Remove a character
		for (int i = 0; i < word.length(); ++i)
			permutations.add(word.substring(0, i) + word.substring(i + 1));
		// Add a new character
		for (int i = 0; i <= word.length(); ++i)
			for (char c = 'a'; c <= 'z'; ++c)
				permutations.add(word.substring(0, i) + String.valueOf(c) + word.substring(i));

		return permutations;
	}

}