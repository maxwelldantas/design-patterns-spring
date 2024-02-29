package com.github.maxwelldantas.designpatternsspring.service;

import com.github.maxwelldantas.designpatternsspring.model.Cliente;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de Cliente.
 * Com isso, se necessário, podemos ter múltiplas implementações dessa mesma interface.
 *
 * @author maxwelldantas
 */
public interface ClienteService {

	Iterable<Cliente> buscarTodos();

	Cliente buscarPorId(Long id);

	void inserir(Cliente cliente);

	void atualizar(Long id, Cliente cliente);

	void deletar(Long id);
}
