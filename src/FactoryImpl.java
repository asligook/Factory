import java.util.HashSet;
import java.util.NoSuchElementException;

public class FactoryImpl implements Factory{
 
	private Holder first;
	private Holder last;
	private Integer size = 0;
	
	@Override
	public String toString() {
		return "FactoryImpl [first=" + first + ", last=" + last + ", size=" + size + "]";
	}
	
	@Override 
	//works
	public void addFirst(Product product) {
		//if the factory line is empty
		if(first == null) {
			Holder newHolder = new Holder(null, product,last);
			first = newHolder;
			last = first;
			size++;
		}
		//if there is at least one element
		else {
			Holder newHolder = new Holder(null, product, first);
			first.setPreviousHolder(newHolder);
			first = newHolder;
			size++;
		}
	}

	@Override
	//works
	public void addLast(Product product) {
		//if the factory line is empty
		if (first == null) {
//			Holder newHolder = new Holder(first, product, null);
//			last = newHolder;
//			first = last;
//			size++;
			addFirst(product);
		}
		//if there is at least one element
		else {
//			Holder newHolder = new Holder(null, product, null);
//			Holder prev = last.getPreviousHolder();
//			last.setNextHolder(newHolder);
//			newHolder.setPreviousHolder(last);
//			size++;
			Holder newHolder = new Holder(null, product, null);
			Holder traverse_holder = first;
			while(traverse_holder.getNextHolder() != null) {
				traverse_holder = traverse_holder.getNextHolder();
			}
			traverse_holder.setNextHolder(newHolder);
			size++;
		}
	}
	
	//works
	@Override
	public Product removeFirst() throws NoSuchElementException {
		//if the factory line is empty there is not any element to remove at first
		//works
		if (first == null) {
			throw new NoSuchElementException();
		}
		// if there is only 1 element
		else if (size ==1) {
			return removeLast();
		}
		//if there are more than 1 elements
		else {
			Product deleted_product = first.getProduct();
			Holder temp = first;
			first = first.getNextHolder();
			temp = null;
			if (first != null) {
				first.setPreviousHolder(null);
			}
			last = last;
			size--;
			return deleted_product;
		}
	}

	//works
	@Override
	public Product removeLast() throws NoSuchElementException {
		//if the factory line is empty there is not any element to remove at first
		if (first == null) {
			throw new NoSuchElementException();
		}
		else {
			// if there is only 1 element
			if (first.getNextHolder() == null) {
				Product deleted_product = first.getProduct();
			    size--;
				first = null;
				last = first;
				return deleted_product;
			}
			// if there are more than 1 elements
			else {
				Holder holder = first;
				while(holder.getNextHolder().getNextHolder() != null) {
					holder = holder.getNextHolder();
				}
				Holder my_last = holder.getNextHolder();
				holder.setNextHolder(null);
				last = my_last;
				my_last = null;
//				last = holder.getNextHolder();
//				holder.setNextHolder(null);
				Product deleted_product = last.getProduct();
				first = first;
				size--;
				return deleted_product;
			}
		}
	}

	//works
	@Override
	public Product find(int id) throws NoSuchElementException {
		// if there is no element nothing can be found
		if (first == null) {
			throw new NoSuchElementException();
		}
		// if there are elements
		else {
			int my_val = 0;
			int i = 0;
			Holder holder = first;
			while(holder != null){
				i++;
				if (holder.getProduct().getId() == id) {
					my_val++;
					return holder.getProduct();
				}
				holder = holder.getNextHolder();
				}
			// if the element does not exist
			if (my_val == 0) {
				throw new NoSuchElementException();
			}
		}
		throw new NoSuchElementException() ;
	}

	//works
	@Override
	public Product update(int id, Integer value) throws NoSuchElementException {
		int old_value = find(id).getValue();
		Product old_product = new Product(id,old_value);
		find(id).setValue(value);
		Product updated_product = find(id);
		return old_product;	
	}

	//works
	@Override
	public Product get(int index) throws IndexOutOfBoundsException {
		//the index cannot be less than 0
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		//the index cannot be greater than size
		if (index >= size) {
			throw new IndexOutOfBoundsException();
		}
		// if the index is between 0 and size it means it is the interval that we have to look at
		if ((index >= 0) && (index < size)) {
			Holder holder = first;
			int traverse = 0;
			while((holder.getNextHolder() != null)&& (traverse != index)){
				holder = holder.getNextHolder();
				traverse++;
				}
			return holder.getProduct();
		}
		throw new IndexOutOfBoundsException();
	}

	//works
	@Override
	public void add(int index, Product product) throws IndexOutOfBoundsException {
		//the index cannot be less than 0
		if (index < 0){
			throw new IndexOutOfBoundsException();
		}
		//the index cannot be greater than size
		if (index > size) {
			throw new IndexOutOfBoundsException();
		}
		//System.out.println(size);
		if (index == 0) {
			addFirst(product);
		}
		// if the index is between 0 and size it means it is the interval that we have to look at
		if ((index > 0) && (index < size)){
			Holder newHolder = new Holder(null, product, null);
			int traverse = 0;
			Holder holder = first;
			while((holder.getNextHolder() != null) && (traverse < (index-1))){
				holder = holder.getNextHolder();
				traverse++;
				//System.out.println(traverse);
			}
			Holder after_inserted = holder.getNextHolder();
			//System.out.println(after_inserted);
			holder.setNextHolder(newHolder);
			newHolder.setNextHolder(after_inserted);
			size++;
		}
		//if the index that we will insert the new product is equal to the size 
		//it means it is the same as "to end insertion"
		if (index == size) {
			addLast(product);
	    }
	}

	@Override
	//works
	public Product removeIndex(int index) throws IndexOutOfBoundsException {
		//the index cannot be less than 0
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		//the index cannot be greater than size
		if (index > size) {
			throw new IndexOutOfBoundsException();
		}
		//it is the same as "delete the first element"
		if ((index == 0)  && (size != 0)){
				return removeFirst();
		}
		if ((index == 0) && (size == 0)){
			throw new IndexOutOfBoundsException();
		}
	
		// if the index is between 0 and size it means it is the interval that we have to look at
		if ((index > 0) && (index < (size-1))){
			int traverse = 0;
			Holder holder = first;
			while((traverse < (index-1))){
//				System.out.println(holder.getProduct());
//				System.out.println(traverse);
				holder = holder.getNextHolder();
				traverse++;
				if (holder.getNextHolder() == null) {
					throw new IndexOutOfBoundsException();
				}
			}
			Product delete = holder.getNextHolder().getProduct();
			if ((holder.getNextHolder() != null) && (holder.getNextHolder().getNextHolder() != null)){
				holder.setNextHolder(holder.getNextHolder().getNextHolder());
				if (holder.getNextHolder().getNextHolder() != null) {
					holder.getNextHolder().getNextHolder().setPreviousHolder(holder);
				}
				first = first;
				last = last;
				size--;
			}
			return delete;

		}
		//it is the same as "delete last element"
		if (index == (size-1)){
			return removeLast();
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	//works
	public Product removeProduct(int value) throws NoSuchElementException {
		//if the factory line is empty the product cannot be found
		if (first == null) {
			throw new NoSuchElementException();
		}
		else {
			// if the item that is going to be deleted is at the first position
			if (first.getProduct().getValue() == value) {
				return removeFirst();
			}
			//if the item that is going to be deleted is at the last position
//			else if (last.getProduct().getValue() == value){
//				return removeLast();
//			}
			//if the element is not at a "special case position" then traverse the factory line
			else {
				Holder holder = first;
				int traverse = 0;
				while(holder.getNextHolder() != null) {
					// if the product is found remove it
					if (holder.getProduct().getValue() == value) {
						int index = traverse;
						return removeIndex(index);
					}
					if (last.getProduct().getValue() == value){
							return removeLast();
						}
					// if we haven't reached the last keep traversing
					if(holder.getProduct().getValue() != value){
						if (traverse < (size-1)) {
							holder = holder.getNextHolder();
						traverse++;
						}
						
						//if we have reached the last element but it is not the item that we were looking for
						//then the element does not exist
						if (traverse == (size-1)) {
							throw new NoSuchElementException();
						}
					}
				}
					
			}
		}
		throw new NoSuchElementException();
	}

	@Override
	public int filterDuplicates() {
		//if the factory line is empty
		if (first == null) {
			return 0;
		}
		//if there is only one element in the factory line there cannot be duplicates
		if (first.getNextHolder() == null) {
			return 0;
		}
		else {
			HashSet<Integer> my_set = new HashSet();  
			Holder holder = first;
			Holder prev_holder = null;
			int result = 0;
			while(holder != null) {
				int my_val = holder.getProduct().getValue();
				if (!(my_set.contains(my_val))) {
					my_set.add(my_val);
					prev_holder = holder;
				}
				else {
					prev_holder.setNextHolder(holder.getNextHolder());
					result++;
					size--;
					//update the last information if necessary
					if (last.getProduct().getValue() == my_val) {
						last = prev_holder;
					}
				}holder = holder.getNextHolder();
			}return result;
			
		}
	}

	//works
	@Override
	public void reverse() {
		//if there is nothing to reverse we cannot invoke this operation so there must be elements in the factory line
		if (first != null) {
			Holder prev_holder = first;
			Holder temp = first;
			Holder traverse_holder = first.getNextHolder();
			Holder end_holder = first;
			prev_holder.setNextHolder(null);
			prev_holder.setPreviousHolder(null);
			
			while(traverse_holder != null) {
				temp = traverse_holder.getNextHolder();
				traverse_holder.setNextHolder(prev_holder);
				prev_holder.setPreviousHolder(traverse_holder);
				prev_holder = traverse_holder;
				traverse_holder = temp;
			}
			first = prev_holder;
			last = end_holder;
		}	
	}

	//works
	//it prints the factory line
	public String printList() {
		String str = "";
		Holder holder = first;
		str = str + "{";
		//if the factory line is empty
		if (first == null) {
			str = str + "}";
			return str;
		}
		// if there is at least one element in the factory line
		else {
		    while(holder.getNextHolder() != null) {
		    	str = str + holder.getProduct() + ",";
		    	holder = holder.getNextHolder();
		    }
		    if (holder.getNextHolder()== null) {
		    	str = str + holder.getProduct();
		    }
		    str = str + "}";
		    return str;
		}
		
	}
	
}