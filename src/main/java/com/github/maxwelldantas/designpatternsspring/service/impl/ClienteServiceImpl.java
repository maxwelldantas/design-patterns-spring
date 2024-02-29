package com.github.maxwelldantas.designpatternsspring.service.impl;

import com.github.maxwelldantas.designpatternsspring.model.Cliente;
import com.github.maxwelldantas.designpatternsspring.model.Endereco;
import com.github.maxwelldantas.designpatternsspring.repository.ClienteRepository;
import com.github.maxwelldantas.designpatternsspring.repository.EnderecoRepository;
import com.github.maxwelldantas.designpatternsspring.service.ClienteService;
import com.github.maxwelldantas.designpatternsspring.service.client.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link org.springframework.beans.factory.annotation.Autowired}).
 * Com isso, como essa classe é um {@link org.springframework.stereotype.Service}, ela será
 * tratada como um <b>Singleton</b>.
 *
 * @author maxwelldantas
 */
@Service
public class ClienteServiceImpl implements ClienteService {


	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	ViaCepService viaCepService;

	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.


	// Strategy: Implementar os métodos definidos na interface.

	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	/**
	 * Busca pelo ID e retorna o Cliente, caso o Cliente não exista retorna nulo.
	 *
	 * @param id
	 * @return Cliente
	 */
	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElse(null);
	}

	@Override
	public void inserir(Cliente cliente) {
		salvarClienteComCep(cliente, null);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteDb = clienteRepository.findById(id);
		if (clienteDb.isPresent()) {
			salvarClienteComCep(cliente, id);
		} else {
			throw new RuntimeException("ID do Cliente informado não existe");
		}

	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}

	private void salvarClienteComCep(Cliente cliente, Long id) {
		// Verificar se o Endereco do Cliente já existe (pelo CEP).
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			// Caso não exista, integrar com o ViaCEP e persistir o retorno.
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		// Inserir Cliente, vinculando o Endereco (novo ou existente).
		if (id == null) {
			clienteRepository.save(cliente);
		} else {
			cliente.setId(id);
			clienteRepository.save(cliente);
		}
	}

}
