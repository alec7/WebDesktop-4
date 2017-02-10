package zkDynamicMenu;

import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
 
public class MainMenuVM {
 
  private List<MenuItem> menuItems;
 private MenuItem selectedMenuItem;
 
  public MenuItem getSelectedMenuItem() {
     return selectedMenuItem;
    }
 
   public void setSelectedMenuItem(MenuItem selectedMenuItem) {
        this.selectedMenuItem = selectedMenuItem;
   }
 
   public List<MenuItem> getMenuItems() {
        return menuItems;
   }
 
   public void setMenuItems(List<MenuItem> menuItems) {
      this.menuItems = menuItems;
 }
 
   public MainMenuVM() {
       menuItems = MenuItemData.getAllMenus();
     setSelectedMenuItem(menuItems.get(0).getChildren().get(0));
 }
 
   @Command
    @NotifyChange("selectedMenuItem")
   public void onMenuClick(@BindingParam("item") MenuItem item) {
      setSelectedMenuItem(item);
  }
}