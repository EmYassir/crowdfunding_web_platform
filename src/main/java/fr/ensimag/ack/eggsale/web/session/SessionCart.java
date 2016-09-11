package fr.ensimag.ack.eggsale.web.session;
import fr.ensimag.ack.eggsale.db.entity.Project;
import java.util.HashMap;
import java.util.Map;


public class SessionCart {
    
    // All Products
    private Map<Long,Integer> items;

    public Map<Long, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Long, Integer> items) {
        this.items = items;
    }
    
    
    
    // Total Amount
    private float amount;
    
    
    
    public SessionCart(){
        amount=0;
        items=new HashMap<Long,Integer>();
    }

    // Getters et Setters
    
    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    
    public void addProduct(Project project){
        if(items.containsKey(project.getId())){
            items.put(project.getId(), items.get(project.getId()) + 1);
        } else {
            items.put(project.getId(), 1);
        }
        
        amount = amount + project.getPrice();
    }
    
   public void removeProduct(Project project){
        if(items.containsKey(project.getId())){
            int newQuantity = items.get(project.getId()) - 1;
            if(newQuantity<=0){
                items.remove(project.getId());
            }
            else{
                items.put(project.getId(), newQuantity);
            }
        } else {
            items.remove(project.getId());
        }
        
        amount = amount - project.getPrice();
    }
   
   public void removeAllProduct(Project project){
        int quantity = 0;
        if(items.containsKey(project.getId())){
            quantity = items.get(project.getId());
            items.remove(project.getId());
        }
        amount = amount - (project.getPrice() * quantity);
    }
   
   public void flush(){
        items = new HashMap<Long,Integer>(); 
        amount = 0;
    }
    
}
