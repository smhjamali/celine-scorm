/**
 * CELINE SCORM
 *
 * Copyright 2014 Adilson Vahldick.
 * https://celine-scorm.googlecode.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.univali.celine.lms.core.commands;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * Esta classe � usada para ler arquivos de dentro do CELINE.JAR. Infelizmente, algumas vers�es dos servidores
 * n�o implementam o InputStream com capacidade do reset. Por isso, fizemos uma vers�o menos eficiente. 
 * 
 * @author adilsonv
 *
 */
public class BinaryFileHandler {

	private static final String folder = "/br/univali/celine/lms/";
	private byte[] content;
	
	private Map<String, byte[]> imagens = new HashMap<String, byte[]>();

	public BinaryFileHandler(HttpServletRequest request, String fileName) throws IOException, URISyntaxException {

		byte[] img = imagens.get(fileName);
		if (img != null) {
			this.content = img;
			return;
		}
		
		InputStream inputStream = getClass().getResourceAsStream(folder + fileName);
		/*
		// como aqui ele retorna um ByteArrayInputStream, os deslocamentos pelo arquivo ser�o bem r�pidos
		long len = 0;
		if (inputStream.getClass().getSimpleName().equals("InflaterInputStream")) { // horrivel !!!
			
		} else {
			len = inputStream.skip(Integer.MAX_VALUE);
			inputStream.reset();	
		}
		*/
		int len = 2048; // bytes. a imagem maxima q temos hoje tem 1134 bytes 
		byte[] dados = new byte[len];
		for (int index = 0; index < dados.length; index++)
        {
			int l = inputStream.read();
			if (l == -1)
				len = index;
			
            dados[index] = (byte) l; 
        }

		dados = Arrays.copyOf(dados, len);
		imagens.put(fileName, dados);
        this.content = dados;
        
	}

	public byte[] getContent() { return this.content; }
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		new BinaryFileHandler(null, "diffDoc.gif");
	}
}
