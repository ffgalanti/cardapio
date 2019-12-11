package br.net.erponline.cardapio.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s.toLowerCase(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static List<Long> decodeLongList(String s) {
		String[] idString = s.split(","); // cria um vetor pegando os valores separados por ","
		
		List<Long> idLong = new ArrayList<>();

		for(String id : idString) {
			if (!"".equals(id)) {
				idLong.add(Long.parseLong(id));
			}
		}
		
		return idLong;

//		USANDO LAMBDA
//		return Arrays.asList(s.split(",")).stream().map(item -> Long.parseLong(item)).collect(Collectors.toList());
	}
}	