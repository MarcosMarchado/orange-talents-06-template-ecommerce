package br.com.marcos.zupacademy.mercadolivre.produto;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UploadFake {

    public List<String> envia(List<MultipartFile> arquivos){
        return arquivos.stream()
                .map(arquivo -> "http://zup.bucket/mercado-livre/"+arquivo.getOriginalFilename()).collect(Collectors.toList());
    }


}
