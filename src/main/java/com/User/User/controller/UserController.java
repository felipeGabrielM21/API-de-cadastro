package com.User.User.controller;


import com.User.User.dto.AtualizarUser;
import com.User.User.dto.ListarUser;
import com.User.User.dto.dadosUser;
import com.User.User.model.User;
import com.User.User.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuarios")
public class UserController {

    private final UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<User> cadastro(@Valid @RequestBody dadosUser dados, UriComponentsBuilder builder) {
        User cadastrarUser = new User(dados);

        repository.save(cadastrarUser);

        var uri = builder.path("/usuarios/{id}").buildAndExpand(cadastrarUser.getId()).toUri();

        return ResponseEntity.created(uri).body(cadastrarUser);
    }


    @Transactional
    @GetMapping("/{id}")
    public ResponseEntity<ListarUser> listar(@PathVariable Long id) {
        var Lista = repository.findById(id);

        if (Lista.isPresent()) {
            User user = Lista.get();
            ListarUser listarUser = new ListarUser(user);
            return ResponseEntity.ok(listarUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<User> atualizar(@RequestBody @Valid AtualizarUser dados) {
        var atualizarCadastros = repository.getReferenceById(dados.id());
        atualizarCadastros.atualizarDados(dados);

        return ResponseEntity.status(HttpStatus.OK).body(repository.save(atualizarCadastros));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir (@PathVariable Long id) {
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cadastro não existe");
        }

        repository.deleteById(id);
        return  ResponseEntity.noContent().build();
    }

}