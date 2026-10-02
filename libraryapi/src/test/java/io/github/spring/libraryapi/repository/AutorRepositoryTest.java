package io.github.spring.libraryapi.repository;

import io.github.spring.libraryapi.model.Autor;
import io.github.spring.libraryapi.model.GeneroLivro;
import io.github.spring.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        var autorSalvo = repository.save(autor);

        System.out.println("Autor Salvo: " + autorSalvo);
    }
    @Test
    public void atualizarTest(){
        var id = UUID.fromString("5906951f-4f85-4be2-a1fb-66a78b64a7d0");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor");
            System.out.println(possivelAutor.get());

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));
            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("5906951f-4f85-4be2-a1fb-66a78b64a7d0");
        repository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("c6d6931b-ea2e-4a3b-82ea-a298a780126f");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Argentino");
        autor.setDataNascimento(LocalDate.of(1970, 1, 31));

        Livro livro1 = new Livro();
        livro1.setIsbn("9888557-859");
        livro1.setPreco(BigDecimal.valueOf(200));
        livro1.setGenero(GeneroLivro.BIOGRAFIA);
        livro1.setTitulo("CS Lewis");
        livro1.setDataPublicacao(LocalDate.of(1980, 1, 2));
        livro1.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("2354354365-859");
        livro2.setPreco(BigDecimal.valueOf(300));
        livro2.setGenero(GeneroLivro.CIENCIA);
        livro2.setTitulo("Universo");
        livro2.setDataPublicacao(LocalDate.of(1980, 1, 2));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro1);
        autor.getLivros().add(livro2);

        repository.save(autor);

        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor(){
        var id = UUID.fromString("afc96647-3fdc-4c3b-933f-b3cd84fcad67");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);
        autor.getLivros().forEach(System.out::println);
    }
}
