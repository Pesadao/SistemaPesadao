package br.com.senai.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import br.com.senai.dao.ClienteDao;
import br.com.senai.dao.ItemVendaDAO;
import br.com.senai.dao.PedidoVendaDAO;
import br.com.senai.model.Cliente;
import br.com.senai.model.ItemVenda;
import br.com.senai.model.PedidoVenda;

@ManagedBean
@SessionScoped
public class PedidoVendaBean {

	private PedidoVenda pedidoVenda = new PedidoVenda();
	private ItemVenda itemVenda = new ItemVenda();
	private List<PedidoVenda> pedidosVendas = new ArrayList<PedidoVenda>();
	private List<ItemVenda> itemVendas = new ArrayList<ItemVenda>();
	private List<Cliente> clientes;
	private boolean skip;


	public void setPedidovenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

	public ItemVenda getItemVenda() {
		return itemVenda;
	}

	public void setItemVenda(ItemVenda itemVenda) {
		this.itemVenda = itemVenda;
	}

	public void save() {
		FacesMessage msg = new FacesMessage("Sucesso", "Welcome :" + PedidoVenda.class.getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public boolean isSkip() {
		return skip;
	}

	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	public String onFlowProcess(FlowEvent event) {
		if (skip) {
			skip = false; // reset in case user goes back
			return "confirm";
		} else {
			return event.getNewStep();
		}
	}

	public PedidoVendaBean() {
		pedidosVendas = new PedidoVendaDAO().listarPedidosVendas();
		clientes = new ClienteDao().listarClientes();
	}

	public String salvar() {
		new PedidoVendaDAO().salvar(pedidoVenda);
		pedidosVendas = new PedidoVendaDAO().listarPedidosVendas();
		ItemVenda itemVenda = new ItemVenda();
		itemVenda.setPedidoVenda(pedidoVenda);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Pedido salvo com sucesso!! "));
				return "pedido_template";
	
		}
	
	public String editar(PedidoVenda pedidoVenda){
		this.pedidoVenda = pedidoVenda;
		return "null";
	}

	public PedidoVenda getPedidoVenda() {
		return pedidoVenda;
	}

	public void setPedidoVenda(PedidoVenda pedidoVenda) {
		this.pedidoVenda = pedidoVenda;
	}

	public List<PedidoVenda> getPedidosVendas() {
		return pedidosVendas;
	}

	public void setPedidosVendas(List<PedidoVenda> pedidosVendas) {
		this.pedidosVendas = pedidosVendas;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<ItemVenda> getItemVendas() {
		return itemVendas;
	}

	public void setItemVendas(List<ItemVenda> itemVendas) {
		this.itemVendas = itemVendas;
	}
}
