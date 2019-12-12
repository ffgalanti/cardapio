package br.net.erponline.cardapio.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.net.erponline.cardapio.services.exceptions.FileException;

@Service
public class ImageService {
//	private static Logger LOG = LoggerFactory.getLogger(ProductService.class);
	
//	@Autowired
//	private ServletContext servletContext;

	@Value("${img.path.http}")
	private String imgPathHttp;

	@Value("${img.path.harddisk}")
	private String imgPathDir;

	@Value("${img.profile.size}")
	private Integer size;

	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			throw new FileException("Somente imagens PNG e JPG são permitidas");
		}
		
		try {
			BufferedImage img = ImageIO.read(uploadedFile.getInputStream());
			if ("png".equals(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

	public BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
		return jpgImage;
	}
	
	public InputStream getInputStream(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());			
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}
	
	public BufferedImage cropSquare(BufferedImage sourceImg) {
		int min = (sourceImg.getHeight() <= sourceImg.getWidth()) ? sourceImg.getHeight() : sourceImg.getWidth();
		return Scalr.crop(
			sourceImg, 
			(sourceImg.getWidth()/2) - (min/2), 
			(sourceImg.getHeight()/2) - (min/2), 
			min, 
			min);		
	}
	
	public BufferedImage resize(BufferedImage sourceImg, int size) {
		return Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, size);
	}

	public URI uploadFile(MultipartFile multipartFile, String pathSubDir, String fileName) {
		ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequestUri();
		URI newUri = builder.build().toUri();
				
		try {
//			String fileNameString = servletContext.getContextPath() + imgPathDir + pathSubDir + "/" + fileName;
			String fileNameString = imgPathDir + pathSubDir + "/" + fileName;
			String fileNameHttp = newUri.getHost() + imgPathHttp + pathSubDir + "/" + fileName;

			BufferedImage jpgImage = this.getJpgImageFromFile(multipartFile);
			jpgImage = this.cropSquare(jpgImage);
			jpgImage = this.resize(jpgImage, size);
		
			InputStream is = this.getInputStream(jpgImage, "jpg");
			
			Path path = Paths.get(fileNameString);
			if (!Files.exists(path)) {
				Files.createDirectories(path);
			}			
			Files.copy(is, Paths.get(fileNameString), StandardCopyOption.REPLACE_EXISTING);
		
			try {
				URI uri = URI.create(fileNameHttp); //Paths.get(fileNameString).toUri();
				return uri;
			} catch (Exception e) {
				return null;
			}						
		} catch (IOException e) {
			throw new FileException(e.getMessage());
		}
	}
}