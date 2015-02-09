package org.zkoss.blog.example.childrenbinding;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;

public class ProductListVM {

	//List
	private List<Product> list;
	private Set<Product> selections_list;
	
	//ListModel
	private ListModelList<Product> model;
	
	public List<Product> getList() {
		return list;
	}
	
	public ListModelList<Product> getModel() {
		return model;
	}
	
	//Product id
	private int count = 0;

	@Init
	public void init() {
		//List
		list = new LinkedList<Product>();
		list.add(new Product(++count, "Telephone", 100));
		list.add(new Product(++count, "NoteBook", 2000));
		list.add(new Product(++count, "Sofa", 2500));
		selections_list = new LinkedHashSet<Product>();
		
		//Model
		model = new ListModelList<Product>();
		model.add(new Product(++count, "Telephone", 100));
		model.add(new Product(++count, "NoteBook", 2000));
		model.add(new Product(++count, "Sofa", 2500));
		model.setMultiple(true);
	}

	@Command
	public void doChecked_list(@BindingParam("checked") boolean checked, @BindingParam("picked") Product p) {
		if (checked)
			selections_list.add(p);
		else
			selections_list.remove(p);
	}
	
	@Command
	public void doChecked_model(@BindingParam("checked") boolean checked, @BindingParam("picked") Product p) {
		if (checked)
			model.addToSelection(p);
		else
			model.removeFromSelection(p);
	}
	
	@Command
	@NotifyChange("list")
	public void add_list() {
		Product newProduct = new Product(++count, "Microwave oven", 999);
		list.add(newProduct);
	}
	
	@Command
	public void add_model() {
		Product newProduct = new Product(++count, "Microwave oven", 999);
		model.add(newProduct);
	}
	
	@Command
	@NotifyChange("list")
	public void edit_list() {
		//List
		Iterator<Product> pi_list = selections_list.iterator();
		while (pi_list.hasNext()) {
			Product p =	pi_list.next();
			int index = list.indexOf(p);
			p.setName(p.getName() + " (updated)");
			list.set(index, p);
		}
		selections_list.clear();
	}
	
	@Command
	public void edit_model() {
		//ListModel
		Iterator<Product> pi_model = model.getSelection().iterator();
		while (pi_model.hasNext()) {
			Product p =	pi_model.next();
			int index = model.indexOf(p);
			p.setName(p.getName() + " (updated)");
			model.set(index, p);
		}
		model.clearSelection();
	}
	
	@Command
	@NotifyChange("list")
	public void remove_list() {
		if (selections_list.size() != 0) {
			list.removeAll(selections_list);
			selections_list.clear();
		} else
			Clients.showNotification("You have to select one!", "warning", null, 225, 250, 2000);
	}

	@Command
	public void remove_model() {
		Set<Product> selections_model = model.getSelection();
		if (selections_model.size() != 0)
			model.removeAll(selections_model);
		else
			Clients.showNotification("You have to select one!", "warning", null, 225, 250, 2000);
	}
}
