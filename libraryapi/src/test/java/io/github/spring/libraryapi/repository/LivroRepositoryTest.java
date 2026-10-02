package io.github.spring.libraryapi.repository;


import io.github.spring.libraryapi.model.GeneroLivro;
import io.github.spring.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest() {
        Livro livro = new Livro();
        livro.setIsbn("9888557-859");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

//        Autor autor = autorRepository
//                .findById(UUID.fromString("afc96647-3fdc-4c3b-933f-b3cd84fcad67"))
//                .orElse(null);
//        livro.setAutor(new Autor());
//
//        repository.save(livro);
    }

    /*@Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("9888557-859");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Joao");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1958, 1, 12));

        livro.setAutor(autor);
        repository.save(livro);
    }*/

   /* @Test
    void atualizarAutorLivro(){
        var livroAtualizar = repository.findById(UUID.fromString("0ddae0e9-6b4a-4a4b-a5e7-3dbeaac61f98")).orElse(null);

        var autor = autorRepository.findById(UUID.fromString("afc96647-3fdc-4c3b-933f-b3cd84fcad67")).orElse(null);

        livroAtualizar.setAutor(autor);

        repository.save(livroAtualizar);
    }*/

    @Test
    void buscarLivroTest() {
        UUID id = UUID.fromString("3ac5680a-ece6-4653-9230-106fb4d2cb7c");
        Livro livro = repository.findById(id).orElse(null);
        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());

        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void perquisaPorTituloTest() {
        List<Livro> lita = repository.findByTitulo("UFO");
        lita.forEach(System.out::println);

    }

    @Test
    void listarGenerosAutoresBrasileirosTest() {
        List<String> generos = repository.listarGenerosAutoresBrasileiros();
        generos.forEach(System.out::println);
    }

   @Test
    void listarLivroPorGeneroTest(){
        var resultao = repository.findByGenero(GeneroLivro.FICCAO, "titulo");
        resultao.forEach(System.out::println);
   }

   @Test
    void deleteporGeneroTest(){
        repository.deleteByGenero(GeneroLivro.BIOGRAFIA);
   }

}

 