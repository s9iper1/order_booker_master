package com.byteshaft.order_booker.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ProductsDetailActivity extends AppCompatActivity {

    private RelativeLayout noteLayout;
    private View separator;

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    // for Adonis
    private List<String> listDataHeaderForAdonis;
    private HashMap<String, List<String>> listDataChildForAdonis;
    /// for Latour
    private List<String> listDataHeaderForLatour;
    private HashMap<String, List<String>> listDataChildForLatour;

    // for Ricaardo
    private List<String> listDataHeaderForRicaardo;
    private HashMap<String, List<String>> listDataChildForRicaardo;



    // for Subz
    private List<String> listDataHeaderForSubz;
    private HashMap<String, List<String>> listDataChildForSubz;

    // for Dip n Dip
    private List<String> listDataHeaderForDipNdip;
    private HashMap<String, List<String>> listDataChildForDipNdip;

    // for ShawarmaBar
    private List<String> listDataHeaderForshawarmaBar;
    private HashMap<String, List<String>> listDataChildForshawarmaBar;

    // for Snack Charboul
    private List<String> listDataHeaderForsnackCharboul;
    private HashMap<String, List<String>> listDataChildForsnackCharboul;

    //for CROISSANTS
    private List<String> listDataHeaderForCroissants;
    private HashMap<String, List<String>>  listDataChildForCroiassants;

    //dagher
    private List<String> listDataHeaderForDagher;
    private HashMap<String, List<String>> listDataChildForDagher;

    //for Z Bureger House
    private List<String> listDataHeaderForBurgerHouse;
    private HashMap<String, List<String>>  listDataChildForBuregerHouse;

    // lilla pizzari
    private List<String> listDataHeaderForPizzari;
    private HashMap<String, List<String>> listDataChildForPizzari;

    //for le pouelt
    private List<String> listDataHeaderForLePouelt;
    private HashMap<String, List<String>>  listDataChildForLePouelt;



    //for Tanbakji
    private List<String> listDataHeaderForTanbakji;
    private HashMap<String, List<String>> listDataChildForTanbakji;

    //for adokit
    private List<String> listDataHeaderForAdokit;
    private HashMap<String, List<String>> listDataChildForAdokit;

    //for Choueiry
    private List<String> listDataHeaderForChoueiry;
    private HashMap<String, List<String>> listDataChildForChoueiry;

     // for falafel el beyt  falafelelbeyt
     private List<String> listDataHeaderForfalafelelbeyt;
     private HashMap<String, List<String>> listDataChildForfalafelelbeyt;

    //  mrgrill

    private List<String> listDataHeaderFormrgrill;
    private HashMap<String, List<String>> listDataChildFormrgrill;

    private List<String> listDataHeaderForhollywood;
    private HashMap<String, List<String>> listDataChildForhollywood;


    private HashMap<String, String[]> priceHashMap;
    private HashMap<String, String[]> newPriceHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        separator = findViewById(R.id.separator_view);
        noteLayout = (RelativeLayout) findViewById(R.id.note_layout);

        if (AppGlobals.getCurrentSelectedStore().equals("Adonis")) {
            separator.setVisibility(View.VISIBLE);
            noteLayout.setVisibility(View.VISIBLE);
        } else {
            separator.setVisibility(View.GONE);
            noteLayout.setVisibility(View.GONE);
        }

        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        expListView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        // Listview on child click listener
        loadSpecificData(AppGlobals.getCurrentSelectedStore());
        System.out.println(AppGlobals.getCurrentSelectedStore());
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeaderForAdonis.get(groupPosition)
                                + " : "
                                + listDataChildForAdonis.get(
                                listDataHeaderForAdonis.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return true;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                // do nothing when expand
                InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (getWindow().getCurrentFocus() != null) {
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    getCurrentFocus().clearFocus();
                }
            }
        });

//        expListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (getWindow().getCurrentFocus() != null) {
//                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//                    getCurrentFocus().clearFocus();
//                }
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//            }
//        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                // do nothing when collapse
                InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (getWindow().getCurrentFocus() != null) {
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    getCurrentFocus().clearFocus();
                }
            }
        });
    }

    private void loadSpecificData(String restaurant) {
        switch (restaurant) {
            case "Adonis":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMap();
                prepareListDataForAdonis();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForAdonis,
                        listDataChildForAdonis, priceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "latour":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                prepareListDataForLatour();
                listAdapter = new ExpandableListAdapter(getApplicationContext(),listDataHeaderForLatour,
                        listDataChildForLatour, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "dip N dip":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                prepareListDataForDipnDip();
                listAdapter = new ExpandableListAdapter(getApplicationContext(),listDataHeaderForDipNdip,
                        listDataChildForDipNdip, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "Subz":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                preparedListDataForSubz();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForSubz,
                        listDataChildForSubz, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;

            case "ricardo_snack":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                preparedListDataForRicaardo();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForRicaardo, listDataChildForRicaardo,
                        newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "burger_house":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                prepareListDataForBurgerHouse();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForBurgerHouse, listDataChildForBuregerHouse, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "le_poulet":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                prepareListDataForLePoulet();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForLePouelt, listDataChildForLePouelt, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "snack_charboul":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                prepareListDataForSnackCharboul();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForsnackCharboul, listDataChildForsnackCharboul, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "dagher":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                prepareDateForDagher();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForDagher, listDataChildForDagher, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "croissant":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                prepareDataForCroissant();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForCroissants, listDataChildForCroiassants, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "pizzaria":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                prePareDataListForPizzari();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForPizzari, listDataChildForPizzari, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "shawarma":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                prepareListDataForShawarmaBar();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForshawarmaBar, listDataChildForshawarmaBar, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;



            case "tan bakji":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                preparedListDataForTanbakji();
                listAdapter = new ExpandableListAdapter(getApplicationContext(),listDataHeaderForTanbakji,
                        listDataChildForTanbakji, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "adokit":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                preparedListDataForAdokit();
                listAdapter = new ExpandableListAdapter(getApplicationContext(),listDataHeaderForAdokit,
                        listDataChildForAdokit, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "Choueiry":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                preparedListDataForChoueiry();
                listAdapter = new ExpandableListAdapter(getApplicationContext(),listDataHeaderForChoueiry,
                        listDataChildForChoueiry, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "falafel el beyt":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                preparedListDataForfalafelelbeyt();
                listAdapter = new ExpandableListAdapter(getApplicationContext(),listDataHeaderForfalafelelbeyt,
                        listDataChildForfalafelelbeyt, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "mrgrill":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                preparedListDataFormrgrill();
                listAdapter = new ExpandableListAdapter(getApplicationContext(),listDataHeaderFormrgrill,
                        listDataChildFormrgrill, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "hollywood":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                preparedListDataForHollywood();
                listAdapter = new ExpandableListAdapter(getApplicationContext(),listDataHeaderForhollywood,
                        listDataChildForhollywood, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;

        }
    }

    private void addPriceDetailsToHashMap() {
        priceHashMap = new HashMap<>();
        priceHashMap.put("Roast beef", new String[]{"6000", "", ""});
        priceHashMap.put("Ham & cheese" ,new String[]{"5000", "", ""});
        priceHashMap.put("Tuna", new String[]{"5000", "", ""});
        priceHashMap.put("Frankfurter", new String[]{"5000", "", ""});
        priceHashMap.put("Tawouk", new String[]{"6000", "", ""});
        priceHashMap.put("Chicken sub", new String[]{"7000", "", ""});
        priceHashMap.put("Soujok", new String[]{"5000", "", ""});
        priceHashMap.put("Makanek", new String[]{"6000", "", ""});
        priceHashMap.put("Sub Marine", new String[]{"6000", "", ""});
        priceHashMap.put("Jumbo burger", new String[]{"8000", "", ""});
        priceHashMap.put("Phili steak", new String[]{"6000", "", ""});
        priceHashMap.put("Kachkawen", new String[]{"4000", "", ""});
        priceHashMap.put("+add cheese", new String[]{"1000", "", ""});
        priceHashMap.put("+add ham&cheese", new String[]{"2000", "", ""});
        priceHashMap.put("Strawberry", new String[]{"6000", "", ""});
        priceHashMap.put("Mixed cocktail", new String[]{"6000", "", ""});
        priceHashMap.put("Pineapple", new String[]{"7000", "", ""});
        priceHashMap.put("Mango", new String[]{"7000", "", ""});
        priceHashMap.put("Guayava", new String[]{"5000", "", ""});
        priceHashMap.put("Orange", new String[]{"4000", "", ""});
        priceHashMap.put("Pomegrenade (winter)", new String[]{"7000", "", ""});
        priceHashMap.put("Lemonade  (summer) ", new String[]{"4000", "", ""});
        priceHashMap.put("Banana milk shake ", new String[]{"4000", "", ""});
        priceHashMap.put("Strawbanana shake", new String[]{"6000", "", ""});
        priceHashMap.put("Strawbanana milk shake", new String[]{"5000", "", ""});
        priceHashMap.put("Melon", new String[]{"4000", "", ""});
        priceHashMap.put("Apple", new String[]{"5000", "", ""});
        priceHashMap.put("Carrot", new String[]{"5000", "", ""});
        priceHashMap.put("Grapefruit", new String[]{"5000", "", ""});
        priceHashMap.put("Strawberry", new String[]{"7000", "", ""});
        priceHashMap.put("Mango", new String[]{"7000", "", ""});
        priceHashMap.put("Strawberry/mango", new String[]{"7000", "", ""});
        priceHashMap.put("Avocado", new String[]{"8000", "", ""});
        priceHashMap.put("Avocado/strawberry", new String[]{"7000", "", ""});
        priceHashMap.put("Avocado/mango", new String[]{"7000", "", ""});
        priceHashMap.put("TRIO", new String[]{"7000", "", ""});
    }

    private void addPriceDetailsToHashMapForLatour() {
        if (AppGlobals.getCurrentSelectedStore().equals("latour")) {
            newPriceHashMap.put("Pizza Marguerita", new String[]{"7000", "9000", "(Tomato sauce, Mozzarella cheese, oregano)"});

            newPriceHashMap.put("Pizza 3 Fromages", new String[]{"7500", "10000", "(Tomato sauce, 3 cheese mix)"});
            newPriceHashMap.put("Pizza Vegetarian", new String[]{"8500", "11000", "(Tomato sauce, artichokes, corn, mushrooms, olives, green pepper, cheese)"});
            newPriceHashMap.put("Pizza Jambon / Dinde", new String[]{"9000", "12000", "(Tomato sauce, ham, mushroom, olives, cheese)"});
            newPriceHashMap.put("Pizza Pepperoni", new String[]{"9000", "12000", "(Tomato sauce, pepperoni, mushrooms, olives, cheese)"});
            newPriceHashMap.put("Pizza Mexichicken", new String[]{"10000", "14000", "(Mexican sauce, chicken, mushroom, cheese)"});

            //  Pasta Fiesta
            newPriceHashMap.put("Penne Arabiata", new String[]{"11000", "", ""});
            newPriceHashMap.put("Chicken Pesto", new String[]{"14000", "", ""});
            newPriceHashMap.put("Carbonara", new String[]{"14000", "", ""});

            // Hot Sandwiches
            newPriceHashMap.put("Tawook", new String[]{"4000", " ", "(Tawook, garlic spread, coleslaw, pickles, French fries)"});
            newPriceHashMap.put("American Frankfurter", new String[]{"5000", " ", "(Hotdog, lettuce, corn, cocktail sauce, French fries)"});
            newPriceHashMap.put("Hamburger", new String[]{"4500", " ", "(Grilled beef meat, lettuce, colseslaw, tomatoes, ketchup, mayo, French fries)"});
//        newPriceHashMap.put("Fish Burger", new String[]{"5000", "", ""});
            newPriceHashMap.put("Fish Burger", new String[]{"5000", " ", "(Fish, lettuce, tartar sauce, pickles, French fries)"});
            newPriceHashMap.put("Fish Filet", new String[]{"5000", " ", "(Grilled  hamour  filet, tartar sauce,  pickles)"});
            newPriceHashMap.put("Chicken Burger", new String[]{"5500", "", "(Grilled chicken breast, garlic mayo spread, tomatoes, lettuce, pickles, French fries)"});
            newPriceHashMap.put("Fajita", new String[]{"6500", " ", "(Marinated chicken, mushrooms, corn, green pepper, onions, melted cheese)"});
            newPriceHashMap.put("Philadelphia", new String[]{"6500", "", "(Beef meat marinated, mushrooms, corn, green pepper, onions, melted cheese)"});
            newPriceHashMap.put("Chicken La Tour", new String[]{"7500", "", "(Grilled chicken, lettuce, corn, mayo sauce)"});
            newPriceHashMap.put("Beef Shawarma", new String[]{"7500", "", "(Marinated beef meat, beef shawarma sauce, parsley, onions, tomatoes, pickles, French fries)"});
            newPriceHashMap.put("Chicken Shawarma", new String[]{"7500", "", "(Chicken, shawarma sauce, lettuce, tomatoes, pickles, French fries)"});
            newPriceHashMap.put("Chicken Submarine", new String[]{"8000", "", "(Chicken, mayo, lettuce, cheddar cheese, tomatoes, pickles, French fries)"});
            newPriceHashMap.put("Chicken Club Sandwish", new String[]{"8500", "", "(Chicken, mayo spread, lettuce, bacon, eggs, cheddar  cheese, served with French  fries and coleslaw)"});

            // Platters
            newPriceHashMap.put("Tawook", new String[]{"12000", "", ""});
            newPriceHashMap.put("Beef Shawarma", new String[]{"13000", "", ""});
            newPriceHashMap.put("Chicken Shawarma", new String[]{"14000", "", ""});
            newPriceHashMap.put("Fish & Chips", new String[]{"14000", "", ""});
            newPriceHashMap.put("Grilled Hamour Filet", new String[]{"14000", "", ""});
            newPriceHashMap.put("Chicken Breast", new String[]{"15000", "", ""});
            newPriceHashMap.put("Chicken Escalope", new String[]{"16000", "", ""});
            newPriceHashMap.put("Chicken Fungi", new String[]{"17000", "", ""});

            // Mana’ish
            newPriceHashMap.put("Zaatar", new String[]{"1000", "", ""});
            newPriceHashMap.put("Zaatar light", new String[]{"1500", "", ""});
            newPriceHashMap.put("Zaatar and Labneh", new String[]{"2000", "", ""});
            newPriceHashMap.put("Zaatar & Cheese", new String[]{"3000", "", ""});
            newPriceHashMap.put("Cheese", new String[]{"3000", "", ""});
            newPriceHashMap.put("Spinach", new String[]{"2500", "", ""});
            newPriceHashMap.put("Keshek", new String[]{"2000", "", ""});
            newPriceHashMap.put("Akkawi", new String[]{"3000", "", ""});
            newPriceHashMap.put("Halloum", new String[]{"3000", "", ""});
            newPriceHashMap.put("Bulgari", new String[]{"3000", "", ""});
            newPriceHashMap.put("Bulgari harra", new String[]{"3000", "", ""});
            newPriceHashMap.put("Cheese Mix", new String[]{"4000", "", ""});
            newPriceHashMap.put("Labneh", new String[]{"3000", "", ""});
            newPriceHashMap.put("Lahme & Kawarma", new String[]{"4000", "", ""});
            newPriceHashMap.put("Lahme b3ajin", new String[]{"3000", "", ""});
            newPriceHashMap.put("Lahme b3ajin & Cheese", new String[]{"4000", "", ""});
            newPriceHashMap.put("Pesto Cheese", new String[]{"3500", "", ""});
            newPriceHashMap.put("Feta Mix", new String[]{"3500", "", ""});
            newPriceHashMap.put("Turkey & Cheese", new String[]{"4000", "", ""});
            newPriceHashMap.put("Ham & Cheese", new String[]{"4000", "", ""});
            newPriceHashMap.put("Salami & Cheese", new String[]{"4000", "", ""});
            newPriceHashMap.put("Hotdog & Cheese", new String[]{"4000", "", ""});
            newPriceHashMap.put("La Tour Special", new String[]{"4000", "", ""});
            newPriceHashMap.put("Soujouk & Cheese", new String[]{"5000", "", ""});
            newPriceHashMap.put("Halloum Bacon", new String[]{"5000", "", ""});
            newPriceHashMap.put("Chocoba", new String[]{"5000", "", ""});

            // Drinks
            newPriceHashMap.put("pepsi", new String[]{"1000", "", ""});
            newPriceHashMap.put("7up", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water", new String[]{"500", "", ""});
        }

        /////////////////////////// Latour Ended ////////////////////////////////

        ////////////////////// Ricaardo Started ///////////////////////////////
        // distinguishing the same itesm using the word  "r" from the hotel Ricaardo
        // so they don't mach with the other hotels items
        if (AppGlobals.getCurrentSelectedStore().equals("ricardo_snack")) {
            newPriceHashMap.put("Philadelphia", new String[]{"6000", "", "(Steak, mushroom, onions, cheese, sauce)"});
            newPriceHashMap.put("Spanish Steak", new String[]{"6000", "", "(Steak, onions, cheese, special sauce)"});
            newPriceHashMap.put("Francisco", new String[]{"6000", "","(Chicken,corn,lettuce,tomato,sauce)"});
            newPriceHashMap.put("Hamburger", new String[]{"5000", "7000", "(Meat,lettuce,tomato,fries,sauce. Add: Bacon, Cheese )"});
            newPriceHashMap.put("Shrimp", new String[]{"7000", "", ""});
            newPriceHashMap.put("Calamari", new String[]{"6000", "", "(Calamari, lettuce, sauce)"});
            newPriceHashMap.put("Roast Beef", new String[]{"6000", "", "(Meat, pickles, tomato, mayo sauce)"});
            newPriceHashMap.put("Grilled chicken breast", new String[]{"5000", "", "chicken, garlic, pickles, lettuce, fries"});
            newPriceHashMap.put("Canarias", new String[]{"6000", "", "(Chicken, mushroom, smoked bacon, cheese)"});
            newPriceHashMap.put("Mexican chicken", new String[]{"6000", "", "(Chicken, mushroom, corn, green pepper, tomato, coriander, onion, garlic, fries, sauce)"});
            newPriceHashMap.put("Ricky", new String[]{"6000", "", "(Chicken breast, fries, lettuce, corn, sauce)"});
            newPriceHashMap.put("Fajita", new String[]{"6000", "", "(Chicken breast, fries, lettuce, corn, sauce)"});

            // drinks
            newPriceHashMap.put("pepsi", new String[]{"1000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"1000", "", ""});
            newPriceHashMap.put("Seven up", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water", new String[]{"500", "", ""});
        }

        ///////////////////////////Ricaardo Ended //////////////////////





        ///////////////////////// SUBZ Started //////////////////////
        if (AppGlobals.getCurrentSelectedStore().equals("Subz")) {

            newPriceHashMap.put("Chicken Caesar", new String[]{"9000", "", "(Chicken, Iceberg, croutons, parmesan cheese)"});
            newPriceHashMap.put("Chef Salad", new String[]{"8000", "", "(Ham & cheese, chicken, iceberg, cherry tomato, corn)"});
            newPriceHashMap.put("Crab Salad", new String[]{"8000", "", "(Crab sticks, iceberg, pineapple, carrots, corn)"});
            newPriceHashMap.put("Tuna Pasta Salad", new String[]{"8000", "", "(Tuna, olives, pasta, fresh mushroom, cherry tomato, corn, iceberg)"});
            newPriceHashMap.put("Subz Salad", new String[]{"9000", "", "(Iceberg, cucumber, fresh mushroom, cherry tomato, thym, black olives, feta cheese, dried grapes walnuts)"});

            // burgers
            newPriceHashMap.put("Classic burger", new String[]{"5000", "", "(Beef patty, coleslaw, tomato, onion, French fries, ketchup, mustard)"});
            newPriceHashMap.put("Subz Burger", new String[]{"6000", "", "(Beef patty, mushroom, onion, cheese, our home made special sauce)"});
            newPriceHashMap.put("Chicken Burger", new String[]{"5500", "", "(Chicken patty, iceberg, pickles, French fries, garlic mayo)"});

            /// SUBz SPECIAL  :

            newPriceHashMap.put("Fajitas", new String[]{"6500", "", "(Chicken marinated, mushroom, onion, green pepper, mayo, mozzarella, iceberg)"});
            newPriceHashMap.put("Philadelphia", new String[]{"6500", "", "(Steak marinated, mushroom, onion, green pepper, mozzarella, mayo sauce)"});
            newPriceHashMap.put("Francisco", new String[]{"6000", "", "(Marinated chicken, mozzarella, corn, pickles, iceberg, soya sauce, mayo)"});
            newPriceHashMap.put("Chicken Sub", new String[]{"6000", "", "(Marinated chicken,  mozzarella, pickles, iceberg, garlic mayo)"});
            newPriceHashMap.put("Mexican Chicken", new String[]{"6500", "", "(Marinated chicken, cheddar cheese, green & red pepper,  corn, onion, spicy, home made sauce,mozzarella)"});
            newPriceHashMap.put("Chicken Escalope", new String[]{"6000", "", "(Breaded fried chicken, coleslaw, French fries, pickles, garlic)"});
            newPriceHashMap.put("Torpedo", new String[]{"6500", "", "(Marinated chicken, mushroom, onion, cheese, iceberg, pickles, fries, garlic mayo)"});
            newPriceHashMap.put("Subz Steak", new String[]{"6500", "", "(Marinated steak, hot paste, onion, mushroom, cheese, home made sauce)"});
            newPriceHashMap.put("Submarine", new String[]{"6500", "", "(Ham & cheese, salami, iceberg, pickles, tomato, mayo)"});
            newPriceHashMap.put("Taouk", new String[]{"5500", "", "(Chicken, garlic, pickles, French fries, coleslaw)"});
            newPriceHashMap.put("Crispy", new String[]{"6500", "", "(Breaded chicken, pickles, lettuce, fries, turkey, cheese, home made sauce)"});
            newPriceHashMap.put("Potato", new String[]{"3000", "", ""});
            newPriceHashMap.put("Crispy Plat", new String[]{"8000", "", "(4 pieces crispy chicken, coleslaw, fries)"});

            // SUBZ FRIES :
            newPriceHashMap.put("French Fries", new String[]{"4000", "", ""});
            newPriceHashMap.put("Wedges", new String[]{"5000", "", ""});
            newPriceHashMap.put("Twister", new String[]{"7000", "", ""});


            // drinks
            newPriceHashMap.put("Pepsi", new String[]{"1000", "", ""});
            newPriceHashMap.put("Seven Up", new String[]{"1000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water Small", new String[]{"500", "", ""});
            newPriceHashMap.put("Water Big", new String[]{"1000", "", ""});
            newPriceHashMap.put("Beer Almaza", new String[]{"2000", "", ""});
            newPriceHashMap.put("Ice Tea", new String[]{"1500", "", ""});
        }
        ///////////////////////// SUBZ Ended ///////////////////////////////////////////////////////

        ///////////////////////// Dip n Dip Started //////////////////////
        if (AppGlobals.getCurrentSelectedStore().equals("dip N dip")) {
            newPriceHashMap.put("Dip n dip crêpe", new String[]{"13000", "", "(strawberry, banana, pineapple, kiwi)"});
            newPriceHashMap.put("Dip n dip crêpe with scoop", new String[]{"16000", "", "icecream(vanilla, chocolate or strawberry)"});
            newPriceHashMap.put("Fettuccini  crêpe full", new String[]{"12500", "", "(crêpe, cut fettuccini style, with 1 scoop of your choice of ice cream, tapped with your choice of chocolate"});
            newPriceHashMap.put("Fettuccini  crêpe small", new String[]{"11000", "", "(crêpe, cut fettuccini style, with 1 scoop of your choice of ice cream, tapped with your choice of chocolate"});
            newPriceHashMap.put("Cinnamon crêpe pouch", new String[]{"10500", "", ""});
            newPriceHashMap.put("Tripple chocolate crêpe", new String[]{"8,000", "", "(Folded crêpe, filled with chocolate and trapped with 3 kinds of chocolate (milk, dark, white)"});
            newPriceHashMap.put("Cookies crêpe", new String[]{"10000", "", "(Folded crêpe, filled with cookies, and tapped with 3 kinds of chocolate(milk, dark, white)"});
            newPriceHashMap.put("Mallow crêpe", new String[]{"10000", "", "(Folded crêpe, filled with marshmallows, and tapped with 3 kinds of chocolate (milk, dark , white)"});
            newPriceHashMap.put("Brownies crêpe", new String[]{"10500", "", "(Folded crêpe, filled with brownies, and tapped with 3 kinds of chocolate (milk, dark, white)"});
            newPriceHashMap.put("Krispy crêpe", new String[]{"10000", "", "(Folded crêpe, filled with ice cream of choice and rice krispies, and tapped with chocolate)"});
            newPriceHashMap.put("Banana wrap", new String[]{"9000", "", "(A full banana wrapped with crêpe and tapped with chocolate and whipped cream"});

            // Waffle:
            newPriceHashMap.put("Dip n dip waffle", new String[]{"13000", "", "(waffle with your choice of fruit " +
                    "(strawberry, banana, pineapple, kiwi) or a mix, all tapped with the great dip n dip real Belgian chocolate of your choice)"});
            newPriceHashMap.put("Dip n dip waffle with ice cream", new String[]{"16000", "", "dip n dip waffle + icecream (vanilla, chocolate or strawberry)"});
            newPriceHashMap.put("Chocolate waffle", new String[]{"9500", "", "(waffle tapped with your choice of chocolate)"});
            newPriceHashMap.put("Waffle stick", new String[]{"8000", "", "(waffle on a stick dipped in your choice of chocolate)"});

            // Pancake:
            newPriceHashMap.put("Pancake mini tower", new String[]{"8000", "", "(5 pieces of pancake tapped with chocolate)"});

            // Chocolate rich
            newPriceHashMap.put("Fondant", new String[]{"9500", "", "(chocolate rich fondant baked fresh" +
                    " , prepared from our famous Belgian chocolate, served with icecream"});
            newPriceHashMap.put("Molten cake", new String[]{"9500", "", "(same as fondant, more liquid, presented in porcelain bowl right from the oven, presented with icecream)"});
            newPriceHashMap.put("Brownies", new String[]{"7500", "", "(Chocolate heavy brownie, served with ice cream, tapped with our amazing chocolate)"});
            newPriceHashMap.put("Dip n dip pizza", new String[]{"9000", "", "(brownies slice trapped with cheesecake and you pick the toppings: strawberry, banana, pineapple, kiwi."});
            newPriceHashMap.put("Chocolate mousse", new String[]{"7000", "", "(the richest chocolate mousse in town made with our famous Belgian chocolate)"});
            newPriceHashMap.put("Trois chocolat mousse", new String[]{"7000", "", "(Three layers of white, milk, dark chocolate mousse)"});
            newPriceHashMap.put("Succès", new String[]{"8000", "", "(very rich chocolate cake with biscuit," +
                    " just like the ones we used to have in home birthday parties, served in 2 slice"});

            // Baked goods
            newPriceHashMap.put("Mini muffin", new String[]{"7000", "", "(4 mini muffins presented with chocolate shot)"});
            newPriceHashMap.put("Cookies", new String[]{"3500", "", "(2 cookies, dipped with our Belgian chocolate )"});
            newPriceHashMap.put("Dip n Dip éclair pyramid", new String[]{"11500", "", "(11 pieces of mini éclair pyramid style tapped with chocolate)"});
            newPriceHashMap.put("Éclair Kebab", new String[]{"6000", "", "(5 pieces of éclair on a stick dipped in chocolate)"});
            newPriceHashMap.put("Profiterole", new String[]{"8000", "", "(Profiterole pastry filled with vanilla ice cream tapped with chocolate)"});

            /// ice creams
            newPriceHashMap.put("Scoop", new String[]{"3000", "", "(strawberry , chocolate, vanilla)"});
            newPriceHashMap.put("scoop Tapped with chocolate", new String[]{"4000", "", ""});
            newPriceHashMap.put("Crunchy ice cream", new String[]{"7500", "", "(A bowl full of ice cream tapped with chocolate and rice Crispies)"});


            newPriceHashMap.put("Chocolate Shot", new String[]{"4000", "", ""});
            newPriceHashMap.put("Chocolate 1kg", new String[]{"60000", "", "Chocolate for your home fountain (1kg)"});

            // Fried yummies
            newPriceHashMap.put("Pain Perdu", new String[]{"9000", "", "(Milk soaked fried toast offered with your choice of caramel and cinnamon or chocolate. With a scoop of icecream"});
            newPriceHashMap.put("Cheese cake nuggets", new String[]{"9000", "", "(5 pieces of fried cheese cake nuggets offered with chocolate)"});

            // stuff in a cup
            newPriceHashMap.put("Dip Crispies", new String[]{"7000", "", "(A cup filled with cereal and chocolate, tapped with mini marshmallows " +
                    "available cereal : rice crispies, honey smacks, trix)"});
            newPriceHashMap.put("Fruits In a cup", new String[]{"7500", "", "(Fruit salad, tapped with chocolate and whipped cream)"});
            newPriceHashMap.put("Brownies in a cup", new String[]{"8500", "", "(Mashed brownies with ice cream and chocolate in a cup)"});

            ///  Dip n Stick
            newPriceHashMap.put("Dip Sticks 4 pieces", new String[]{"3500", "", "filled with - Strawberry, Kiwi, Marshmallow, Brownies, Pineapple, Banana"});
            newPriceHashMap.put("Dip Sticks 8 pieces", new String[]{"6000", "", "filled with -Strawberry, Kiwi, Marshmallow, Brownies, Pineapple, Banana"});
            newPriceHashMap.put("Dip stick platter", new String[]{"16500", "", "filled with 4 pieces of  7 of your choice from of the following items with chocolate fondue,"});

            //Dip n dip mania
            newPriceHashMap.put("Dip-able items (full plate)", new String[]{"3500", "", "(Strawberry, pineapple, kiwi, banana, marshmallow)"});
            newPriceHashMap.put("Brownies, mini éclair", new String[]{"4000", "", ""});
            newPriceHashMap.put("Ice cream (scoop)", new String[]{"3000", "", ""});
            newPriceHashMap.put("Fondue", new String[]{"7000", "", ""});
            newPriceHashMap.put("Whipped cream", new String[]{"1000", "", ""});


            // COLD DRINKS :
            newPriceHashMap.put("Dip n dip freezy", new String[]{"9500", "", "(The richest chocolate drink ever)"});

            // Milk shakes :
            newPriceHashMap.put("Chocolate milk shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Vanilla milk shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Strawberry  milk shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Coffee shake", new String[]{"9000", "", ""});
            newPriceHashMap.put("Banana milk shake", new String[]{"7500", "", ""});
            newPriceHashMap.put("After eight milk shake", new String[]{"7500", "", "(Mint flavored chocolate milk shake)"});

            // Frappe
            newPriceHashMap.put("Mocha frappe", new String[]{"7500", "", "(Chocolate, espresso in a cold icy creamy mixture)"});
            newPriceHashMap.put("White mocha frappe", new String[]{"7500", "", "(White chocolate, espresso in a cold icy creamy mixture)"});
            newPriceHashMap.put("Caramel frappe", new String[]{"7500", "", "(Caramel flavored frappe)"});
            newPriceHashMap.put("Cookies frappe", new String[]{"8000", "", "(Frappe blended with real cookies)"});
            newPriceHashMap.put("Pink frappe", new String[]{"7500", "", "(Frappe with  strawberry and raspberry flavors)"});

            // Smoothies :
            newPriceHashMap.put("Creamy smoothies", new String[]{"8000", "", ""});
            newPriceHashMap.put("Smoothie float", new String[]{"9000", "", ""});
            newPriceHashMap.put("Oreo milk shake", new String[]{"8000", "", "(original Oreo cookies)"});
            newPriceHashMap.put("Affogato", new String[]{"5000", "", "(A scoop of vanilla ice cream drowned in espresso with your favorite flavor -Caramel -Hazelnut -Cinnamon)"});

            /// Ice tea shakes :
            newPriceHashMap.put("Ice tea shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Lemon tea shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Mint tea shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Peach tea shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Raspberry  tea shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Iced cappuccino", new String[]{"7000", "", ""});

            // Soft  drinks
            newPriceHashMap.put("Ice tea", new String[]{"6000", "", "(- Peach   - Raspberry  - Lemon  - Mixed berry)"});
            newPriceHashMap.put("Italian soda", new String[]{"5000", "", "(- Lemon  - Raspberry   - Cherry   - Strawberry   - Peach   - Blue curacao)"});
            newPriceHashMap.put("Flavored cola", new String[]{"5000", "", "(- Vanilla    - Lemon    - Cherry )"});
            newPriceHashMap.put("Pop", new String[]{"3000", "", "(- Pepsi   - Diet pepsi   - 7up   - Diet 7up)"});
            newPriceHashMap.put("Perrier", new String[]{"3000", "", ""});
            newPriceHashMap.put("Redbull", new String[]{"6000", "", ""});
            newPriceHashMap.put("Spring water", new String[]{"1500", "", ""});
        }

        if (AppGlobals.getCurrentSelectedStore().equals("shawarma")) {


            ///////////////////////// Shawarma Bar Started //////////////////////

            newPriceHashMap.put("Chicky Shawarma", new String[]{"3500", "", "(Garlic, pickles, lettuce, fries)"});
            newPriceHashMap.put("Juicy Beef Shawarma", new String[]{"4000", "", "(Tarator, tomato, parsley, pickles, onion, fries)"});
            newPriceHashMap.put("Beef Shawarma Bar", new String[]{"4000", "", "(Tarator, tomato, parsley, pickles, onion and fries topped with our secret sauce)"});
            newPriceHashMap.put("Shawarma Bites", new String[]{"13000", "", "(Beef or chicken  - 10 pcs)"});
            newPriceHashMap.put("Shawarma Soujouk", new String[]{"4000", "", "(Tomato, parsley, pickles, onion and fries topped with our secret sauce)"});

            /// Our special combo
            newPriceHashMap.put("2 shawarma djej", new String[]{"9000", "", "(with coleslow,fries,soft drinks)"});
            newPriceHashMap.put("1 shawarma lahme 1 shawarma djej", new String[]{"9000", "", "(with coleslow,fries,soft drinks)"});
            newPriceHashMap.put("2 shawarma lahme", new String[]{"9000", "", "(with coleslow ,fries,soft drinks)"});
            newPriceHashMap.put("Chicken", new String[]{"15000", "", "(500g of shawarma with vegetables)"});
            newPriceHashMap.put("Beef", new String[]{"18000", "", "(500g of shawarma with vegetables)"});

            /// Soft  drinks
            newPriceHashMap.put("Pepsi", new String[]{"1000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"1000", "", ""});
            newPriceHashMap.put("Seven up", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water small", new String[]{"750", "", ""});


            ///////////////////////// Shawarma Bar End //////////////////////
        }
        if (AppGlobals.getCurrentSelectedStore().equals("snack_charboul")) {

            /////////////////////////  Snak charboul Started /////////////////////


            newPriceHashMap.put("Lahme", new String[]{"5000", "", ""});
            newPriceHashMap.put("Tawouk", new String[]{"4000", "", ""});
            newPriceHashMap.put("Kabab", new String[]{"4000", "", ""});
            newPriceHashMap.put("Tawouk Special", new String[]{"5000", "", ""});
            newPriceHashMap.put("Steak", new String[]{"4000", "", ""});
            newPriceHashMap.put("Steak Special", new String[]{"5000", "", ""});
            newPriceHashMap.put("Asabit Djej", new String[]{"4000", "", ""});
            newPriceHashMap.put("Makanek", new String[]{"4000", "", ""});
            newPriceHashMap.put("Soujouk", new String[]{"4000", "", ""});
            newPriceHashMap.put("Hamburger", new String[]{"4000", "", ""});
            newPriceHashMap.put("Cheese Burger", new String[]{"5000", "", ""});
            newPriceHashMap.put("Chicken Burger", new String[]{"4000", "", ""});
            newPriceHashMap.put("Fajita", new String[]{"5000", "", ""});
            newPriceHashMap.put("Philadelphia", new String[]{"5000", "", ""});
            newPriceHashMap.put("Rosto", new String[]{"4000", "", ""});
            newPriceHashMap.put("Hot Dog", new String[]{"4000", "", ""});
            newPriceHashMap.put("Hot Dog Special", new String[]{"5000", "", ""});
            newPriceHashMap.put("Nkha3at", new String[]{"5000", "", ""});
            newPriceHashMap.put("Sanesil", new String[]{"5000", "", ""});
            newPriceHashMap.put("Batata", new String[]{"3000", "", ""});
            newPriceHashMap.put("Kafta Naye", new String[]{"4000", "", ""});
            newPriceHashMap.put("Habra Naye", new String[]{"4000", "", ""});
            newPriceHashMap.put("Shawarma Lahme", new String[]{"4000", "", ""});
            newPriceHashMap.put("Shawarma Djej", new String[]{"4000", "", ""});
            newPriceHashMap.put("Lsenet", new String[]{"5000", "", ""});
            newPriceHashMap.put("Djej", new String[]{"4000", "", ""});

            /// PLATES:
            newPriceHashMap.put("Lahme Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Tawouk Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Kabab Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Tawouk Special Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Steak Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Steak Special Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Asabit Djej Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Makanek Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Soujouk Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Hamburger Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Cheese Burger Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Chicken Burger Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Fajita Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Philadelphia Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Rosto Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Hot Dog Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Hot Dog Special Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Nkha3at Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Sanesil Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Batata Plate", new String[]{"5000", "", ""});
            newPriceHashMap.put("Kafte Naye Plate", new String[]{"5000", "", ""});
            newPriceHashMap.put("Habra Naye Plate", new String[]{"5000", "", ""});
            newPriceHashMap.put("Shawarma Lahme Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Shawarma Djej Plate", new String[]{"4000", "", ""});
            newPriceHashMap.put("Lsenet Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Djej Plate", new String[]{"6000", "", ""});

            // COLD   BEVERAGES :
            newPriceHashMap.put("Water Small", new String[]{"500", "", ""});
            newPriceHashMap.put("Water Big", new String[]{"1000", "", ""});
            newPriceHashMap.put("Pepsi", new String[]{"1000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"1000", "", ""});
            newPriceHashMap.put("7-Up", new String[]{"1000", "", ""});
            newPriceHashMap.put("Beer", new String[]{"2000", "", ""});

            /////////////////////////  Snak charboul End //////////////////////
        }

        if (AppGlobals.getCurrentSelectedStore().equals("croissant")) {

            //////////////////////// start croissants  ///////////////////////

            // croissants

            newPriceHashMap.put("Chocolate", new String[]{"3000", "", ""});
            newPriceHashMap.put("Knéfé", new String[]{"4000", "", ""});
            newPriceHashMap.put("Zaatar", new String[]{"3000", "", ""});
            newPriceHashMap.put("Zaatar Special", new String[]{"3500", "", ""});
            newPriceHashMap.put("Olive & Mint", new String[]{"3000", "", ""});
            newPriceHashMap.put("Olive & Mint Special", new String[]{"3500", "", ""});
            newPriceHashMap.put("Spicy Cheese", new String[]{"3500", "", ""});
            newPriceHashMap.put("Spicy Cheese Special", new String[]{"3500", "", ""});
            newPriceHashMap.put("Cheese & Ham", new String[]{"3500", "", ""});
            newPriceHashMap.put("Cheese & Ham Special", new String[]{"4000", "", ""});
            newPriceHashMap.put("12 Small Croissant of any kind", new String[]{"10000", "", ""});

            //cake & dozens
            newPriceHashMap.put("Tartez", new String[]{"10000", "", ""});
            newPriceHashMap.put("Éclair", new String[]{"10000", "", ""});
            newPriceHashMap.put("Chocolate Cream", new String[]{"10000", "", ""});

            // cake pieces
            newPriceHashMap.put("Baba au Rhum", new String[]{"3000", "", ""});
            newPriceHashMap.put("Baba au Rhum Special", new String[]{"4000", "", ""});
            newPriceHashMap.put("Éclair", new String[]{"3000", "", ""});
            newPriceHashMap.put("Éclair Special", new String[]{"4000", "", ""});
            newPriceHashMap.put("Tarte aux Fruits", new String[]{"3000", "", ""});
            newPriceHashMap.put("Sablé Chocolat", new String[]{"3000", "", ""});
            newPriceHashMap.put("Sablé Jam", new String[]{"3000", "", ""});
            newPriceHashMap.put("Mille Feuilles", new String[]{"3000", "", ""});
            newPriceHashMap.put("Boule Chocolat Coco", new String[]{"3000", "", ""});
            newPriceHashMap.put("Boule Chocolat Almond", new String[]{"3000", "", ""});
            newPriceHashMap.put("Cheese Cake", new String[]{"3000", "", ""});
            newPriceHashMap.put("Swiss Roll", new String[]{"3000", "", ""});

            //fresh juice
            newPriceHashMap.put("Orange", new String[]{"3000", "", ""});
            newPriceHashMap.put("Grape Fruit", new String[]{"4000", "", ""});
            newPriceHashMap.put("Carrot", new String[]{"3000", "", ""});
            newPriceHashMap.put("Melon", new String[]{"5000", "", ""});
            newPriceHashMap.put("Avocado", new String[]{"7000", "", ""});
            newPriceHashMap.put("Apple", new String[]{"5000", "", ""});
            newPriceHashMap.put("Strawberry", new String[]{"5000", "", ""});
            newPriceHashMap.put("Lemonade", new String[]{"4000", "", ""});
            newPriceHashMap.put("Banana With Milk", new String[]{"6000", "", ""});
            newPriceHashMap.put("Kiwi, Strawberry & Lemon", new String[]{"6000", "", ""});
            newPriceHashMap.put("Polo", new String[]{"5000", "", ""});
            newPriceHashMap.put("Cocktail Pieces", new String[]{"6000", "", ""});
            newPriceHashMap.put("Cocktail Juice", new String[]{"6000", "", ""});

            // cold beverages

            newPriceHashMap.put("Pepsi", new String[]{"2000", "", ""});
            newPriceHashMap.put("7-Up", new String[]{"2000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"2000", "", ""});
            newPriceHashMap.put("Mexican Beer", new String[]{"5000", "", ""});
            newPriceHashMap.put("Beer", new String[]{"4000", "", ""});
            newPriceHashMap.put("AMP Energy", new String[]{"2000", "", ""});
            newPriceHashMap.put("H2OH", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water (small)", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water (big)", new String[]{"2000", "", ""});
            newPriceHashMap.put("Nuts", new String[]{"2000", "", ""});
            newPriceHashMap.put("Chips", new String[]{"2000", "", ""});

            //fresh mocktails

            newPriceHashMap.put("Strawberry Freez", new String[]{"6000", "", "(Strawberry puree, water, fresh strawberry, ice)"});
            newPriceHashMap.put("Mango Freez", new String[]{"6000", "", "(Mango puree, water, fresh mango slice, ice)"});
            newPriceHashMap.put("Mango Orange Freez", new String[]{"6000", "", "(Mango puree, orange juice, ice, bloody orange syrup)"});
            newPriceHashMap.put("Peach Freez", new String[]{"6000", "", "(Peach  Puree, water, ice,  fresh peach  slice)"});
            newPriceHashMap.put("Tropical Smoothie", new String[]{"6000", "", "(Peach puree, water, ice,  mango puree, strawberry puree)"});
            newPriceHashMap.put("Survivor Smoothie", new String[]{"6000", "", "(Mango puree, water,  ice, pineapple slice,  passion  fruit puree,  coconut  puree)"});
            newPriceHashMap.put("Hulk", new String[]{"6000", "", "(Apple puree, peach puree, lemon, kiwi   puree,  water, ice)"});
            newPriceHashMap.put("Passion Fruit Reez", new String[]{"6000", "", "(Passion  puree,  water, ice)"});
            newPriceHashMap.put("Be Cool", new String[]{"6000", "", "(Strawberry puree, passion  fruit  puree, banana  puree)"});
            newPriceHashMap.put("Italian Lemonade", new String[]{"6000", "", "(Sweet & Sour mix, 7-up,  fresh lemon,  strawberry puree, basil  syrup, ice)"});
            newPriceHashMap.put("Blue Hawaiian Lemonade", new String[]{"6000", "", "(Sweet  &  Sour  mix, 7-Up,  fresh lemon,  coconut puree, blue  curacao  syrup,  ice)"});
            newPriceHashMap.put("Pink Lemonade", new String[]{"6000", "", "(Sweet & Sour mix,  7-Up, fresh lemon,  pink grapefruit  syrup,  grenadine  syrup, ice)"});
            newPriceHashMap.put("Frozen Bloody Orange", new String[]{"6000", "", "(Fresh  orange  juice,  grapefruit  syrup,  bloody  orange  syrup, ice)"});
            newPriceHashMap.put("Orange Mango Smoothie", new String[]{"6000", "", "(Mango puree, orange juice,  ice)"});
            newPriceHashMap.put("Kiwi", new String[]{"6000", "", "(Kiwi  puree,  banana puree,  orange  juice,  ice)"});
            newPriceHashMap.put("Bubble Gum", new String[]{"6000", "", "(Bubble  gum  syrup,  cranberry  juice,  lemon  juice, ice)"});
            newPriceHashMap.put("Virgin Colada", new String[]{"6000", "", "(Coconut  puree,  pineapple  juice,  blue  curacao,  ice)"});


            //frozen shake

            newPriceHashMap.put("Coconut Shake", new String[]{"6000", "", "(coconut  puree, milk,  ice)"});
            newPriceHashMap.put("Melon Shake", new String[]{"6000", "", "(Melon, milk, ice)"});
            newPriceHashMap.put("Strawberry Shake", new String[]{"6000", "", "(Strawberry  puree, milk,  vanilla powder, ice)"});
            newPriceHashMap.put("Vanilla Shake", new String[]{"6000", "", "(Vanilla  syrup,  vanilla  powder,  milk,  ice)"});
            newPriceHashMap.put("Minted Iced Chocolate", new String[]{"6000", "", "(Organic   sweet  chocolate  powder,  dark  chocolate  sauce, green mint  syrup,  vanilla  powder,  ice)"});
            newPriceHashMap.put("Choco Oreo Shake", new String[]{"6000", "", "(Milk,  chocolate  cookies  syrup, oreo  cookies,  white  chocolate powder, ice)"});
            newPriceHashMap.put("Banana Berry Shake", new String[]{"6000", "", "(Milk,  red berries  puree, banana  puree, vanilla  powder,  fresh  banana, ice)"});
            newPriceHashMap.put("Chocolate Milk Shake", new String[]{"6000", "", "(Milk, chocolate  flavor, dark  chocolate  powder, ice)"});
            newPriceHashMap.put("Snickers Milk Shake", new String[]{"6000", "", "(Milk, dark  chocolate, peanut  butter,  mocha  powder,  caramel  sauce, ice)"});
            newPriceHashMap.put("Black & White", new String[]{"6000", "", "(Milk  , one scoop vanilla ice cream , lemon slice, vanilla powder, chocolate sauce, coffee beans, pineapple  juice)"});
            newPriceHashMap.put("Bubble Gum Shake", new String[]{"6000", "", "(Bubble  gum  ice  cream, bubble  gym  syrup,  milk, water, ice)"});

            // icecream

            newPriceHashMap.put("Chocolat Mou", new String[]{"6000", "", ""});
            newPriceHashMap.put("Chikita", new String[]{"6000", "", "(3 scoops  ice cream , fruit  salad, whipped  cream)"});
            newPriceHashMap.put("Banana Split", new String[]{"6000", "", "(Banana  slices, fruits  slices,, whipped  cream, 3 scoops  ice cream)"});

            // ICED   COFFEE   DRINKS

            newPriceHashMap.put("Coffee Frappe", new String[]{"5000", "", "(Espresso, sugar, ice)"});
            newPriceHashMap.put("Nescafe Frappe", new String[]{"5000", "", ""});
            newPriceHashMap.put("Cappuccino Frappe", new String[]{"5000", "", "(Espresso,  milk,  original  mocafé  powder, ice)"});
            newPriceHashMap.put("Caramel Frappucino", new String[]{"5000", "", "(Milk, caramel   sauce,  original  mocafé  powder, ice)"});
            newPriceHashMap.put("Toffee Banana Mocha", new String[]{"5000", "", "(Milk,  banana  puree,  toffee  nuts  syrup,  mocha  powder,  ice)"});
            newPriceHashMap.put("Frozen Tiramisu", new String[]{"5000", "", "(Espresso, milk,   white  chocolate  powder,  tiramisu  syrup,  ice)"});
            newPriceHashMap.put("After Eight Mocha", new String[]{"5000", "", "(Mint  syrup,  mocafé,  milk, ice)"});
            newPriceHashMap.put("Long Island Frappe", new String[]{"5000", "", "(Coconut  syrup  or puree, mocafé, milk, ice)"});
            newPriceHashMap.put("Belgian Fever", new String[]{"5000", "", "(Speculoos  syrup, mocafé, milk, ice)"});
            newPriceHashMap.put("Crunchy", new String[]{"5000", "", "(Milk, vanilla  powder, cookies,  whipped  cream, ice)"});

            // hot beverages

            newPriceHashMap.put("Turkish Coffee", new String[]{"4000", "5000", "(2 cups)  4000 L.L\n  ( 3 cups )  5000 L.L\n"});
            newPriceHashMap.put("Nescafe", new String[]{"3000", "", ""});
            newPriceHashMap.put("Doppio", new String[]{"4000", "", "(Espresso  double)"});
            newPriceHashMap.put("Cappuccino", new String[]{"3000", "", ""});
            newPriceHashMap.put("Flavored Latté", new String[]{"5000", "", "(Espresso, milk,  dark  chocolate  sauce)"});
            newPriceHashMap.put("Mochaccino", new String[]{"5000", "", "(Espresso, milk, dark  chocolate  sauce)"});
            newPriceHashMap.put("Caramel Macchiato", new String[]{"5000", "", "(Espresso, milk, caramel  sauce)"});
            newPriceHashMap.put("Tiramisu Latté", new String[]{"5000", "", "(Espresso, milk,  tiramisu  syrup)"});
            newPriceHashMap.put("Chocolate Cookies Latté", new String[]{"5000", "", "(Espresso, milk,  chocolate  cookies  syrup)"});
            newPriceHashMap.put("Belgian Latté", new String[]{"5000", "", "(Espresso, milk, caramel  sauce, cookies  syrup)"});
            newPriceHashMap.put("Crusty Mocha", new String[]{"5000", "", "(Espresso, milk,  chocolate  sauce,  hazelnut  syrup)"});
            newPriceHashMap.put("Flavored Hot Chocolate(hazelnut)", new String[]{"5000", "", "(Chocolate, milk, hazelnut  flavor)"});
            newPriceHashMap.put("Flavored hot chocolate(vanilla)", new String[]{"5000", "", "(Chocolate, milk,  vanilla  flavor)"});
            newPriceHashMap.put("Tea With Milk", new String[]{"4000", "", ""});

        }
            /////////////////////////////////////////// end croissants ////////////////////

            /////////////////////////////////////////// Z burger house STARTED ////////////////
            if (AppGlobals.getCurrentSelectedStore().equals("burger_house")) {

                newPriceHashMap.put("Classic Sandwich", new String[]{"6000", "", "(Grilled beef patty with lettuce ,Tomato, sliced onions , pickles ketchup & mustard)"});
                newPriceHashMap.put("Classic Combo", new String[]{"11000", "", "(Grilled beef patty with lettuce ,Tomato, sliced onions , pickles ketchup & mustard)"});
                newPriceHashMap.put("Cheese burger Sandwich", new String[]{"6500", "", "(Grilled beef patty with cheddar cheese, Lettuce, Tomato , sliced onions ,pickles ,Ketchup & mustard )"});
                newPriceHashMap.put("Cheese burger Combo", new String[]{"11500", "", "(Grilled beef patty with cheddar cheese, Lettuce, Tomato , sliced onions ,pickles ,Ketchup & mustard )"});
                newPriceHashMap.put("Biggy house burger Sandwich", new String[]{"9000", "", "(double grilled beef patty with double cheddar cheese , lettuce , Tomato , onion , pickles & special Biggy house sauce)"});
                newPriceHashMap.put("Biggy house burger Combo", new String[]{"14000", "", "(double grilled beef patty with double cheddar cheese , lettuce , Tomato , onion , pickles & special Biggy house sauce)"});
                newPriceHashMap.put("BBq pepperoni Sandwich", new String[]{"8500", "", "(Grilled beef patty  with cheddar cheese , crispy pepperoni , fresh mushrooms , lettuce , tomato , pickles & bbq sauce)"});
                newPriceHashMap.put("BBq pepperoni Combo", new String[]{"13500", "", "(Grilled beef patty  with cheddar cheese , crispy pepperoni , fresh mushrooms , lettuce , tomato , pickles & bbq sauce)"});
                newPriceHashMap.put("Z Lebanese Burger Sandwich", new String[]{"6000", "", "(Grilled beef patty , coleslaw , tomato , pickles , onion, fries, grilled bread, Z sauce)"});
                newPriceHashMap.put("Z Lebanese Burger Combo", new String[]{":11000", "", "(Grilled beef patty , coleslaw , tomato , pickles , onion, fries, grilled bread, Z sauce)"});
                newPriceHashMap.put("Steak burger Sandwich", new String[]{"9000", "", "200G of grilled steak filet, lettuce, tomato, onions , Z sauce ,  Fresh Mushroom)"});
                newPriceHashMap.put("Steak burger Combo", new String[]{"14000", "", "200G of grilled steak filet, lettuce, tomato, onions , Z sauce ,  Fresh Mushroom)"});
                newPriceHashMap.put("Deluxe burger Sandwich", new String[]{"8000", "", "(Grilled beef patty with cheddar cheese, fried onion rings , lettuce , pickles and sweet onion rings )"});
                newPriceHashMap.put("Deluxe burger Combo", new String[]{"13000", "", "(Grilled beef patty with cheddar cheese, fried onion rings , lettuce , pickles and sweet onion rings )"});
                newPriceHashMap.put("Chili cheese burger Sandwich", new String[]{"7000", "", "(grilled beef patty with cheddar cheese , lettuce, grilled green pepper & onions, pickles & spicy sauce with caramelized onions )"});
                newPriceHashMap.put("Chili cheese burger Combo", new String[]{"12000", "", "(grilled beef patty with cheddar cheese , lettuce, grilled green pepper & onions, pickles & spicy sauce with caramelized onions )"});
                newPriceHashMap.put("Bacon house Sandwich", new String[]{"8500", "", "(Grilled beef patty with cheddar cheese , crispy bacon , avocado slices , Lettuce, Pickles  & Mild burger house sauce )"});
                newPriceHashMap.put("Bacon house Combo", new String[]{"13500", "", "(Grilled beef patty with cheddar cheese , crispy bacon , avocado slices , Lettuce, Pickles  & Mild burger house sauce )"});
                newPriceHashMap.put("Mozzarella beef burger Sandwich", new String[]{"9000", "", "(Grilled beef patty with breaded froed Mozzarella cheese , lettuce , tomato , pickles & burger house sauce )"});
                newPriceHashMap.put("Mozzarella beef burger Combo", new String[]{"14000", "", "(Grilled beef patty with breaded froed Mozzarella cheese , lettuce , tomato , pickles & burger house sauce )"});
                newPriceHashMap.put("Swiss mushroom Sandwich", new String[]{"8000", "", "(Grilled beef patty with melted Swiss cheese & creamy mushroom sauce )"});
                newPriceHashMap.put("Swiss mushroom Combo", new String[]{"13000", "", "(Grilled beef patty with melted Swiss cheese & creamy mushroom sauce )"});
                newPriceHashMap.put("Sushi burger Sandwich", new String[]{"9500", "", "(Avocado, crab , wasabi sauce , sweet corn ,rice , special sauce )"});
                newPriceHashMap.put("Sushi burger Combo", new String[]{"14500", "", "(Avocado, crab , wasabi sauce , sweet corn ,rice , special sauce )"});
                newPriceHashMap.put("Z angus house Sandwich", new String[]{"9000", "", "(180 G of angus beef , lettuce , pickles , Tomato , Z sauce )"});
                newPriceHashMap.put("Z angus house Combo", new String[]{"14000", "", "(180 G of angus beef , lettuce , pickles , Tomato , Z sauce )"});
                newPriceHashMap.put("Cheese at heart burger Sandwich", new String[]{"9000", "", "(180 G  Meat filled with cheese , Lettuce , tomato , pickles , cheddar slice , crispy nachos )"});
                newPriceHashMap.put("Cheese at heart burger Combo", new String[]{"14000", "", "(180 G  Meat filled with cheese , Lettuce , tomato , pickles , cheddar slice , crispy nachos )"});
                newPriceHashMap.put("Mexican house Sandwich", new String[]{"9000", "", "(180 G of Meat filled with Mexican spices & sauces , tomato , lettuce ,pickles )"});
                newPriceHashMap.put("Mexican house Combo", new String[]{"14000", "", "(180 G of Meat filled with Mexican spices & sauces , tomato , lettuce ,pickles )"});
                newPriceHashMap.put("Soujouk burger Sandwich", new String[]{"7500", "", "(120 G of grilled soujouk beef, lettuce , tomato, pickles , mayo, z sauce)"});
                newPriceHashMap.put("Soujouk burger Combo", new String[]{"12500", "", "(120 G of grilled soujouk beef, lettuce , tomato, pickles , mayo, z sauce)"});
                newPriceHashMap.put("Veggie burger Sandwich", new String[]{"6500", "", "(lettuce, tomato, onions, fried mozzarella veggie , pickles , veggie sauce)"});
                newPriceHashMap.put("Veggie burger Combo", new String[]{"11500", "", "(lettuce, tomato, onions, fried mozzarella veggie , pickles , veggie sauce)"});
                newPriceHashMap.put("Kids meal", new String[]{"9000", "", "(2 mini burgers beef or chicken , French fries , orange juice or soft drink )"});
                newPriceHashMap.put("Minions", new String[]{"11000", "", "(3 mini burgers , French fries )"});

                // sub sandwiches

                newPriceHashMap.put("Francisco Sandwich", new String[]{"7500", "", "(Marinated grilled chicken breast, cheese, lettuce, corn, pickles, biggy  sauce)"});
                newPriceHashMap.put("Francisco  Combo", new String[]{"12500", "", "(Marinated grilled chicken breast, cheese, lettuce, corn, pickles, biggy  sauce)"});

                newPriceHashMap.put("Fajita Sandwich", new String[]{"12500", "", "(Marinated grilled chicken breast, cheese, lettuce, corn, grilled peppers & onions, pickles, guacamole)"});
                newPriceHashMap.put("Fajita Combo", new String[]{"7000", "", "(Marinated grilled chicken breast, cheese, lettuce, corn, grilled peppers & onions, pickles, guacamole)"});

                newPriceHashMap.put("Chicken Sub  Sandwich", new String[]{"7500", "", "(Marinated  grilled  chicken  breast, cheese, lettuce, tomato, corn, pickles & light aioli sauce)"});
                newPriceHashMap.put("Chicken Sub Combo", new String[]{"12500", "", "(Marinated  grilled  chicken  breast, cheese, lettuce, tomato, corn, pickles & light aioli sauce)"});

                newPriceHashMap.put("Mexican Sandwich", new String[]{"12500", "", "(Spicy  marinated  grilled  chicken  breast , cheese, cheddar, lettuce, shredded  carrots grilled, red peppers, corn, pickles, spicy sauce with caramelized onios)"});
                newPriceHashMap.put("Mexican Combo", new String[]{"12500", "", "(Spicy  marinated  grilled  chicken  breast , cheese, cheddar, lettuce, shredded  carrots grilled, red peppers, corn, pickles, spicy sauce with caramelized onios)"});

                newPriceHashMap.put("Chicken Swiss  Sandwich", new String[]{"7500", "", "(Grilled chicken breast marinated in teriyaki sauce, creamy mushroom sauce, swiss cheese)"});
                newPriceHashMap.put("Chicken Escalope  Sandwich", new String[]{"8000", "", "(Crispy chicken, coleslaw, smoked  cheese & ham , corn, pickles, biggy sauce)"});
                newPriceHashMap.put("Cuban Sandwich", new String[]{"8000", "", "(Crispy chicken, coleslaw, smoked  cheese & ham , corn, pickles, biggy sauce)"});

                newPriceHashMap.put("Chicken Escalope Combo", new String[]{"13000", "", "(Spicy marinated  grilled  beef,cheddar cheese, lettucfe, tomato  tomato, grilled onion,Pickles, mushroom,corn,Z sauce)"});
                newPriceHashMap.put("Beef Avocado Sandwich", new String[]{"8500", "", "(Marzinated Beef Fillet ,Melted,Mozzarella,cheese, Lettuce,Avocado,Corn,Tomato,smoked ham , sweet onion sauce)"});

                newPriceHashMap.put("Beef Avocado Combo", new String[]{"13500", "", "(Marinated Beef Fillet ,Melted,Mozzarella,cheese, Lettuce,Avocado,Corn,Tomato,smoked ham , sweet onion sauce)"});

                newPriceHashMap.put("Philly Steak Sandwich", new String[]{"7500", "", "(Marinated Beef fillet,melted cheddar cheese, lettuce,tomato,grilled red pepper and onions,black pepper sauce)"});
                newPriceHashMap.put("Philly Steak Combo", new String[]{"13000", "", "(Marinated Beef fillet,melted cheddar cheese, lettuce,tomato,grilled red pepper and onions,black pepper sauce)"});

                newPriceHashMap.put("Crab Sub Sandwich", new String[]{"11000", "", "(Sheeded crab with coleslaw, sweet corn , avocado slices & cocktail sauce)"});
                newPriceHashMap.put("Crab Sub Combo", new String[]{"6500", "", "(Sheeded crab with coleslaw, sweet corn , avocado slices & cocktail sauce)"});

                newPriceHashMap.put("Tuna Sub Sandwich", new String[]{"6000", "", "(Chunky tuna with lettuce , sweet corn ,pickles& cucumbermayo sauce)"});
                newPriceHashMap.put("Tuna Sub Combo", new String[]{"11000", "", "(Chunky tuna with lettuce , sweet corn ,pickles& cucumbermayo sauce)"});

                newPriceHashMap.put("Roast beef Sub Sandwich", new String[]{"6000", "", ""});
                newPriceHashMap.put("Roast beef Sub Combo", new String[]{"11000", "", ""});






                /// APPETIZERS
                newPriceHashMap.put("Fries basket", new String[]{"4000", "", "(extra crispy French fries)"});
                newPriceHashMap.put("Cheese fries basket", new String[]{"6000", "", "(extra crispy French fries with cheddar)"});
                newPriceHashMap.put("curly fries basket", new String[]{"6500", "", "(extra crispy French fries with cheddar)"});
                newPriceHashMap.put("baked potatoes", new String[]{"7500", "", "(2 pieces  of baked potato served with sauces  and cheddar  cheese upon request )"});
                newPriceHashMap.put("Potato wedges basket", new String[]{"6000", "", "(extra crispy wedges served  with dipping sauce as requested )"});
                newPriceHashMap.put("Mozzarella sticks", new String[]{"6000", "", "(breaded mozzarella , served with fries & Z sauce)"});
                newPriceHashMap.put("Cheddar balls", new String[]{"6000", "", "(Breaded cheddar , served with fries & z sauce )"});
                newPriceHashMap.put("crispy crab or calamar", new String[]{"7500", "", "(fried crispy crab  or calamar served with fries and z sauce )"});
                newPriceHashMap.put("chicken tenders", new String[]{"8500", "", "(marinated strips of 100% chicken breast served with optional sauce )"});
                newPriceHashMap.put("chicken tenders grilled", new String[]{"8500", "", "(grilled strips 100% chicken breast served with optional sauce)"});
                newPriceHashMap.put("Chicken tenders dipped", new String[]{"9500", "", "(dipped marinated strips of 100% chicken breast served with optional sauce)"});
                newPriceHashMap.put("special crispy wings", new String[]{"9000", "", "(extra crispy wings  served with mixed or dipping sauces as requested sauces available : bbq- buffalo – blue cheese – honey mustard – thousand island – sweet chilli )"});
                newPriceHashMap.put("appetizers combo", new String[]{"15000", "", "(cheddar balls, crispy wings, crispy crab, crispy calamar, mozzarella sticks, potato wedges , coleslaw)"});

                //// SALADS:
                newPriceHashMap.put("Season salad", new String[]{"8500", "", "(iceberg lettuce, rocca, grated carrots, sliced black olives, cucumber , tomato & sweet corn )"});
                newPriceHashMap.put("Halloumi salad", new String[]{"9000", "", "(grilled halloum with iceberg  lettuce , rocca, cucumber, tomato, sliced black olive & fresh mint pesto )"});
                newPriceHashMap.put("Greek salad", new String[]{"9000", "", "(iceberg lettuce , rocca , cucumber, tomato, sliced black olives , mint , green pepper & Feta cheese)"});
                newPriceHashMap.put("Tuna Pasta", new String[]{"9500", "", "(fusilli pasta , tuna ,  grated carrots, sweet corn, fresh mushrooms, olives & tomato )"});
                newPriceHashMap.put("Chef salad", new String[]{"10000", "", "(Smoked  ham with  emmental  cheese , iceberg  lettuce, cucumber, sweet  corn &  tomato)"});
                newPriceHashMap.put("Steak Salad", new String[]{"11000", "", "(Iceberg  lettuce  &  rocca  topped  with  grilled beef  filet, grilled onions  & tomato)"});
                newPriceHashMap.put("Crispy Chicken  Salad", new String[]{"10000", "", "(Crispy  chicken  breast, iceberg  lettuce, sweet  corn & tomato)"});
                newPriceHashMap.put("Chicken Caesar", new String[]{"10000", "", "(Iceberg  lettuce  topped  with  grilled chicken, parmesan  cheese,  Caesar  dressing  &  croutons)"});
                newPriceHashMap.put("Crab Salad", new String[]{"10500", "", "(Iceberg  lettuce, rocca, sweet corn, fresh mushrooms, fresh avocado, tomato & chredded crab )"});
                newPriceHashMap.put("Rocca Salad", new String[]{"9000", "", "(Iceberg  lettuce,  fresh mushrooms, tomato, parmesan cheese, balsamic  sauce)"});
                newPriceHashMap.put("Burger House Salad", new String[]{"11000", "", "(Iceberg  lettuce topped  with  grilled chicken , crispy bacon, shaved cheese, avocado, grated  carrots, sweet corn, fresh mushrooms & tomato)"});

                /// BURGER  (FRESH CHICKEN)
                newPriceHashMap.put("Chicken Breast Burger Sandwich", new String[]{"7000", "", "(Grilled chicken breast, lettuce, pickles, garlic, fries)"});
                newPriceHashMap.put("Chicken Breast Burger Combo", new String[]{"12000", "", "(Grilled chicken breast, lettuce, pickles, garlic, fries)"});
                newPriceHashMap.put("Med Burger Sandwich", new String[]{"7500", "", "(Marinated grilled  chicken breast in fresh coriander, fresh garlic, lettuce, pickles, med sauce)"});
                newPriceHashMap.put("Med Burger Combo", new String[]{"12500", "", "(Marinated grilled  chicken breast in fresh coriander, fresh garlic, lettuce, pickles, med sauce)"});
                newPriceHashMap.put("Crispy Chicken Burger Sandwich", new String[]{"13000", "", "(Crispy chicken patty with cheddar cheese, lettuce, pickles, biggy sauce)"});
                newPriceHashMap.put("Crispy Chicken Burger Combo", new String[]{"8000", "", "(Crispy chicken patty with cheddar cheese, lettuce, pickles, biggy sauce)"});
                newPriceHashMap.put("Crunchy Chicken Fillet Sandwich", new String[]{"8000", "", "(Crunchy  chicken  fillet, cheddar  cheese, lettuce, pickles, spicy bbq sauce)"});
                newPriceHashMap.put("Crunchy Chicken Fillet Combo", new String[]{"13000", "", "(Crunchy  chicken  fillet, cheddar  cheese, lettuce, pickles, spicy bbq sauce)"});

                //// FISH
                newPriceHashMap.put("Crunchy Fishy Fish Sandwich", new String[]{"7000", "", "(Deep fried fish, cheddar cheese, lettuce, pickles, house tartar sauce)"});
                newPriceHashMap.put("Crunchy Fishy Fish Combo", new String[]{"12000", "", "(Deep fried fish, cheddar cheese, lettuce, pickles, house tartar sauce)"});
                newPriceHashMap.put("Fried Fishy Burger Sandwich", new String[]{"7000", "", "(Sauce tartar, lettuce, tomato, mustard, fried fish)"});
                newPriceHashMap.put("Fried Fishy Burger Combo", new String[]{"12000", "", "(Sauce tartar, lettuce, tomato, mustard, fried fish)"});
                newPriceHashMap.put("Grilled Fishy Burger Sandwich", new String[]{"6500", "", "(Lettuce, garlic, pickles, fried potato, grilled fish)"});
                newPriceHashMap.put("Grilled Fishy Burger Combo", new String[]{"11500", "", "(Lettuce, garlic, pickles, fried potato, grilled fish)"});


                ///broasted quality
                newPriceHashMap.put("Whole Broasted chicken", new String[]{"18000", "", "(Broasted potatoes & garlic cream)"});
                newPriceHashMap.put("Half Broasted chicken", new String[]{"10000", "", "(Broasted potatoes & garlic cream)"});
                newPriceHashMap.put("Combo: Broasted Chicken", new String[]{"15000", "", ""});
                newPriceHashMap.put("Combo: crispy tenders", new String[]{"15000", "", "(tender , marinated strips 100 chicken breast , meat , French fries, coleslaw salad , dipping sauce & soft drink)"});
                newPriceHashMap.put("Broasted Potatoes", new String[]{"6500", "", ""});


                ///// Z Platters

                newPriceHashMap.put("Z 3esmanliyé  Chicken", new String[]{"19000", "", "(500 G  of 3esmanliyé marinated  chicken, filled with cheese  &  mushrooms )"});
                newPriceHashMap.put("White House Chicken", new String[]{"6500", "", "(500 G  of grilled  chicken, covered  with  white sauce,  French  fries,  coleslaw)"});
                newPriceHashMap.put("Chicken Escalope", new String[]{"6500", "", "(500 G of fried  escalope  chicken,  French  fries,  coleslaw)"});
                newPriceHashMap.put("Philadelphia Platter", new String[]{"6500", "", "(500 G grilled  Philadelphia  served  with  vegetables  mix,  side  sauces,  special  bread)"});
                newPriceHashMap.put("Fajita Platter", new String[]{"6500", "", "(500 G  of fajita  served  with grilled  vegetables  with  special  sauces  and  grilled  or fried  potato  with fajita  bread  apart)"});
                newPriceHashMap.put("Salmon Platter", new String[]{"6500", "", "(350 G of  fresh  grilled  salmon,  served  with rice, vegetables  mix & special sauce)"});


            }
            ///////////////////////////////////  Z burger House End //////////////////////////////

            if (AppGlobals.getCurrentSelectedStore().equals("le_poulet")) {

            ////////////////////////////////// Le Pouelt  Satarted ///////////////////////////////

            newPriceHashMap.put("Season Salad", new String[]{"6000", "", ""});
            newPriceHashMap.put("Rocca Salad", new String[]{"7000", "", ""});
            newPriceHashMap.put("Chicken Salad", new String[]{"9000", "", ""});
            newPriceHashMap.put("Coleslaw Salad", new String[]{"5000", "", ""});
            newPriceHashMap.put("Tuna Salad", new String[]{"8000", "", ""});
            newPriceHashMap.put("Fattouch", new String[]{"8000", "", ""});
            newPriceHashMap.put("Fattouch (large)", new String[]{"13000", "", ""});

            /// POTATOES
            newPriceHashMap.put("French Fries (plate)", new String[]{"4000", "", ""});
            newPriceHashMap.put("French Fries (large)", new String[]{"7000", "", ""});
            newPriceHashMap.put("French Fries (box)", new String[]{"3000", "", ""});
            newPriceHashMap.put("Broasted Potatoes", new String[]{"8000", "", ""});

            ////// STARTERS
            newPriceHashMap.put("Hommos", new String[]{"5000", "", ""});
            newPriceHashMap.put("Moutabal", new String[]{"5000", "", ""});

            // SANDWICH   3AL  FAHEM
            newPriceHashMap.put("Shish Tawouk", new String[]{"5000", "", ""});
            newPriceHashMap.put("Shish Tawouk Platter", new String[]{"7000", "", ""});
            newPriceHashMap.put("Lahme (lamb meat)", new String[]{"5000", "", ""});
            newPriceHashMap.put("Kabab", new String[]{"4000", "", ""});
            newPriceHashMap.put("Chicken", new String[]{"4000", "", ""});
            newPriceHashMap.put("Steak", new String[]{"5000", "", ""});
            newPriceHashMap.put("Fish Filet", new String[]{"5000", "", ""});
            newPriceHashMap.put("Hamburger", new String[]{"4000", "", ""});
            newPriceHashMap.put("Cheese Burger", new String[]{"5000", "", ""});
            newPriceHashMap.put("Chicken Burger", new String[]{"5000", "", ""});
            newPriceHashMap.put("Falafel", new String[]{"2500", "", ""});
            newPriceHashMap.put("Makanek", new String[]{"4000", "", ""});
            newPriceHashMap.put("Soujouk", new String[]{"4000", "", ""});
            newPriceHashMap.put("Shawarma Lahme", new String[]{"4000", "", ""});
            newPriceHashMap.put("Shawarma djej", new String[]{"3000", "", ""});
            newPriceHashMap.put("Shawarma Lahme (large)", new String[]{"6000", "", ""});
            newPriceHashMap.put("Shawarma Djej (large)", new String[]{"5000", "", ""});
            newPriceHashMap.put("Shawarma Lahme (Saj bread)", new String[]{"7000", "", ""});
            newPriceHashMap.put("Shawarma Djej (Saj bread)", new String[]{"6000", "", ""});
            newPriceHashMap.put("Chicken Liver", new String[]{"5000", "", ""});
            newPriceHashMap.put("Fajita", new String[]{"5000", "", ""});
            newPriceHashMap.put("Crispy", new String[]{"5000", "", ""});
            newPriceHashMap.put("Rosto", new String[]{"4000", "", ""});

            // FARROUJ  GHAZ
            newPriceHashMap.put("Grilled  Chicken", new String[]{"13000", "", ""});
            newPriceHashMap.put("half Grilled Chicken", new String[]{"7000", "", ""});
            newPriceHashMap.put("Grilled Chicken Special", new String[]{"15000", "", ""});

            ///Broasted  Chicken
            newPriceHashMap.put("Chicken", new String[]{"18000", "", ""});
            newPriceHashMap.put("Drumsticks (10pcs)", new String[]{"20000", "", ""});
            newPriceHashMap.put("Legs (8pcs)", new String[]{"18000", "", ""});
            newPriceHashMap.put("Wings (12pcs)", new String[]{"14000", "", ""});
            newPriceHashMap.put("Wings With Coriander (24pcs)", new String[]{"18000", "", ""});

            // PLATTERS :
            newPriceHashMap.put("Chicken Filet (light)", new String[]{"16000", "", "(baked potatoes, lettuce, 300g chicken breast)"});
            newPriceHashMap.put("Meat Filet (light)", new String[]{"18000", "", "(baked potatoes, lettuce, 300g meat)"});
            newPriceHashMap.put("Fish Filet (light)", new String[]{"14000", "", ""});
            newPriceHashMap.put("Shawarma Djej", new String[]{"10000", "", ""});
            newPriceHashMap.put("Shawarma Lahme", new String[]{"10000", "", ""});

            /// DJEJ  3AL FAHEM :
            newPriceHashMap.put("Chicken", new String[]{"15000", "", ""});
            newPriceHashMap.put("Legs (8pcs)", new String[]{"15000", "", ""});
            newPriceHashMap.put("Wings (12 pcs)", new String[]{"14000", "", ""});
            newPriceHashMap.put("Wings With Coriander (12pcs)", new String[]{"18000", "", ""});

            // MACHEWE
            newPriceHashMap.put("1 Kg Lahme Che2af", new String[]{"40000", "", ""});
            newPriceHashMap.put("1 Kg Tawouk", new String[]{"30000", "", ""});
            newPriceHashMap.put("1 Kg Kabab", new String[]{"35000", "", ""});
            newPriceHashMap.put("1 Kg Machewe Mchakal", new String[]{"35000", "", ""});

            /// DESSERT  &  BEVERAGES
            newPriceHashMap.put("Jello", new String[]{"3000", "", ""});
            newPriceHashMap.put("Custard", new String[]{"3000", "", ""});
            newPriceHashMap.put("Laban 3iran", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water (small)", new String[]{"500", "", ""});
            newPriceHashMap.put("Water (big)", new String[]{"1000", "", ""});
            newPriceHashMap.put("Pepsi", new String[]{"1000", "", ""});
            newPriceHashMap.put("7-Up", new String[]{"1000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"1000", "", ""});
            newPriceHashMap.put("Pepsi (1.25 L)", new String[]{"2000", "", ""});
            newPriceHashMap.put("7-Up (1.25 L)", new String[]{"2000", "", ""});
            newPriceHashMap.put("Mirinda (1.25 L)", new String[]{"2000", "", ""});
            newPriceHashMap.put("Pepsi (2L)", new String[]{"2500", "", ""});
            newPriceHashMap.put("7-Up (2L)", new String[]{"2500", "", ""});
            newPriceHashMap.put("Mirinda (2L)", new String[]{"2500", "", ""});
            /////////////////////////////////////// le Poulet ended ///////////////////////
//            newPriceHashMap.put("", new String[]{"", "", ""});
//            newPriceHashMap.put("", new String[]{"", "", ""});
//            newPriceHashMap.put("", new String[]{"", "", ""});
//            newPriceHashMap.put("", new String[]{"", "", ""});
        }

        if (AppGlobals.getCurrentSelectedStore().equals("dagher")) {
            newPriceHashMap.put("Samak", new String[]{"4000", "", ""});
            newPriceHashMap.put("Chich Tawouk", new String[]{"5000", "", ""});
            newPriceHashMap.put("Kabab", new String[]{"5000", "", ""});
            newPriceHashMap.put("Lahme Che2af", new String[]{"5000", "", ""});
            newPriceHashMap.put("2asabit Djej", new String[]{"4000", "", ""});
            newPriceHashMap.put("Ma2ani2 Lahme", new String[]{"4000", "", ""});
            newPriceHashMap.put("Crab", new String[]{"4000", "", ""});
            newPriceHashMap.put("Sejou2", new String[]{"4000", "", ""});
            newPriceHashMap.put("Marquise", new String[]{"4000", "", ""});
            newPriceHashMap.put("Hot Dog", new String[]{"4000", "", ""});
            newPriceHashMap.put("Hamburger", new String[]{"4000", "", ""});
            newPriceHashMap.put("Cheese Burger", new String[]{"5000", "", ""});
            newPriceHashMap.put("Chicken Burger", new String[]{"5000", "", ""});
            newPriceHashMap.put("Potato", new String[]{"3000", "", ""});
            newPriceHashMap.put("Machewe Mchakal (1 kilo)", new String[]{"35000", "", ""});
            newPriceHashMap.put("Sa7en Machewe (small)", new String[]{"6000", "", ""});
            newPriceHashMap.put("Sa7en Machewe (large)", new String[]{"12000", "", ""});
            newPriceHashMap.put("Potato (big)", new String[]{"5000", "", ""});
            newPriceHashMap.put("Shawarma Lahme", new String[]{"5000", "", ""});
            newPriceHashMap.put("Shawarma Djej", new String[]{"5000", "", ""});
            newPriceHashMap.put("Saniyit Shawarma Lahme (big)", new String[]{"30000", "", ""});
            newPriceHashMap.put("Saniyit Shawarma Lahme (small)", new String[]{"15000", "", ""});
            newPriceHashMap.put("Saniyit Shawarma Djej (big)", new String[]{"30000", "", ""});
            newPriceHashMap.put("Saniyit Shawarma Djej (small)", new String[]{"15000", "", ""});
            newPriceHashMap.put("Sandwich Falefil", new String[]{"3000", "", ""});
            newPriceHashMap.put("Saniyit Falefil (big)", new String[]{"10000", "", ""});
            newPriceHashMap.put("Saniyit Falefil (small)", new String[]{"5000", "", ""});
            newPriceHashMap.put("Broasted", new String[]{"17000", "", ""});
            newPriceHashMap.put("Broasted (sa7en)", new String[]{"10000", "", ""});
            newPriceHashMap.put("Broasted Fish (1 kilo)", new String[]{"17000", "", ""});
            newPriceHashMap.put("Broasted Fish (sa7en)", new String[]{"10000", "", ""});
            newPriceHashMap.put("Pepsi", new String[]{"1000", "", ""});
            newPriceHashMap.put("7-UP", new String[]{"1000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water (big)", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water (small)", new String[]{"500", "", ""});
            newPriceHashMap.put("Laban 3iran", new String[]{"1000", "", ""});
        }

        if (AppGlobals.getCurrentSelectedStore().equals("pizzaria")) {
            newPriceHashMap.put("Margherita", new String[]{"10000", "", "(tomato sauce, cheese)"});
            newPriceHashMap.put("Funghi", new String[]{"13000", "", "(frensh mushroom, corn2)"});
            newPriceHashMap.put("Four cheese", new String[]{"17000", "", "(kashkaval cheese, mozzarella, roquefort, parmesan)"});
            newPriceHashMap.put("Rocca pizza", new String[]{"17000", "", "(rocca, tomato, feta cheese, parmesan cheese)"});
            newPriceHashMap.put("Basil & cherry", new String[]{"16000", "", "(fresh basil, cherry tomato, parmesan cheese)"});
            newPriceHashMap.put("Vegetarian", new String[]{"16000", "", "(onion, fresh mushroom, green pepper, olive, corn, artichoke)"});
            newPriceHashMap.put("Vegetarian delight", new String[]{"18000", "", "(onion, tomato, fresh mushroom, artichoke, asparagus, olive, corn)"});
            newPriceHashMap.put("Broccoli pizza", new String[]{"17000", "", "(broccoli, feta cheese, tomato8)"});
            newPriceHashMap.put("Vesuvio", new String[]{"14000", "", "(Ham)"});
            newPriceHashMap.put("Hawaiian", new String[]{"15000", "", "(ham, pineapple2)"});
            newPriceHashMap.put("Capricciosa", new String[]{"15000", "", "(ham, fresh mushroom)"});
            newPriceHashMap.put("Lebanese pizza", new String[]{"16000", "", "(ham, fresh mushroom, olive, green pepper, corn)"});
            newPriceHashMap.put("Pepperoni (beef)", new String[]{"14000", "", "(Pepperoni)"});
            newPriceHashMap.put("Pepperoni (pork)", new String[]{"14000", "", "(pepperoni)"});
            newPriceHashMap.put("Salami", new String[]{"16000", "", "(without pizza sauce salami , tomato, asperge, kashkaval & mozzarella cheese)"});
            newPriceHashMap.put("Du chef", new String[]{"16000", "", "(beef meat, onion, fresh mushroom, green pepper, bbq sauce)"});
            newPriceHashMap.put("Primavera", new String[]{"18000", "", "(tomatobeef meat, bacon, egg, onion)"});
            newPriceHashMap.put("Carlo", new String[]{"18000", "", "(beef meat, pepperoni, ham)"});
            newPriceHashMap.put("BBQ chicken", new String[]{"16000", "", "(breast chicken, onion, green pepper, corn, bbq sauce11)"});
            newPriceHashMap.put("Campagnolia", new String[]{"15000", "", "(pepperoni, fresh mushroom, onion)"});
            newPriceHashMap.put("Calzone", new String[]{"16000", "", "(bolognese sauce (mince meat, onion, green pepper, carrots), parmesan cheese)"});
            newPriceHashMap.put("Mexicana chicken", new String[]{"19000", "", "(breast chicken, green pepper, corn , olive, avocado , mexican hot sauce)"});
            newPriceHashMap.put("Eldorado beef", new String[]{"19000", "", "(tomato, fresh mushroombeef meat, bacon, onion, green pepper, chilli slice)"});
            newPriceHashMap.put("Lilla italy supreme", new String[]{"20000", "", "(beef meat, ham, pepperoni, onion,  green  pepper, fresh mushroom, tomato)"});
            newPriceHashMap.put("Soujouk pizza", new String[]{"20000", "", "(green peppersoujouk, onion, tomato)"});
            newPriceHashMap.put("Pastrami pizza", new String[]{"18000", "", "(pastrami, rocca, tomato)"});
            newPriceHashMap.put("Pescatore", new String[]{"18000", "", "(shrimp, tuna)"});
            newPriceHashMap.put("Paradiso", new String[]{"18000", "", "(shrimp, ham, fresh mushroom)"});
            newPriceHashMap.put("Marinara", new String[]{"18000", "", "(shrimp, mussel)"});
            newPriceHashMap.put("Super tefat", new String[]{"18000", "", "(shrimp, ham, fresh mushroom)"});
            newPriceHashMap.put("Seafood", new String[]{"18000", "", "(shrimp, crab, mussel)"});
            newPriceHashMap.put("Quattro stagion", new String[]{"20000", "", "(large shrimp, crab, ham, fresh mushroom, olive)"});
            newPriceHashMap.put("Anchovy", new String[]{"20000", "", "(anchovy, artichoke, asparagus)"});
            newPriceHashMap.put("Super seafood", new String[]{"22000", "", "(large shrimp, crab, tuna, mussel, avocado)"});
            newPriceHashMap.put("Beef", new String[]{"7000", "", "(beef meat, lettuce, tomato, cucumber, onion)"});
            newPriceHashMap.put("Chicken", new String[]{"7000", "", "(chicken schnitzel, lettuce, tomato, corn)"});
            newPriceHashMap.put("Tuna", new String[]{"7000", "", "(tuna, lettuce, tomato, olive, corn, onion)"});
            newPriceHashMap.put("Fish", new String[]{"7000", "", "(lettuce, pickles, corn)"});
            newPriceHashMap.put("Beef burger", new String[]{"6000", "", "(beef, pickles, lettuce, tomato)"});
            newPriceHashMap.put("Cheese burger", new String[]{"7000", "", "(beef, cheese, pickles, lettuce, tomato)"});
            newPriceHashMap.put("Chicken burger", new String[]{"6000", "", "(chicken, tomato, lettuce, pickles)"});
            newPriceHashMap.put("Bacon & egg burger", new String[]{"6000", "", "(bacon, egg, onion, tomato, bbq sauce)"});
            newPriceHashMap.put("Fish burger", new String[]{"6000", "", "(fish, lettuce, pickles)"});
            newPriceHashMap.put("Extra meat", new String[]{"3000", "", ""});
            newPriceHashMap.put("Extra chicken", new String[]{"3000", "", ""});
            newPriceHashMap.put("Fish & chips plate", new String[]{"15000", "", "(fried fish, lettuce, corn, chips, tartar dressing)"});
            newPriceHashMap.put("French fries", new String[]{"6000", "", ""});
            newPriceHashMap.put("Extra dressing", new String[]{"1000", "", ""});
            newPriceHashMap.put("Panachee", new String[]{"16000", "", "(palmito, asparagus, corn, artichoke, lettuce, tomato, mushroom)"});
            newPriceHashMap.put("Tuna", new String[]{"6000", "", "(tuna, corn, olive, lettuce, tomato, cucumber, lemon, onion)"});
            newPriceHashMap.put("Chicken", new String[]{"7000", "", "(chicken, avocado, lettuce, tomato, cucumber, corn, parmesan cheese)"});
            newPriceHashMap.put("Greek", new String[]{"16000", "", "(feta cheese, olive, green pepper, lettuce, tomato, cucumber, onion)"});
            newPriceHashMap.put("Shrimp", new String[]{"18000", "", "(large shrimp, avocado, corn, lettuce, tomato, cucumber, lemon)"});
            newPriceHashMap.put("Crab", new String[]{"17000", "", "(crab, lettuce, corn, cherry tomato)"});
            newPriceHashMap.put("Ham & cheese", new String[]{"15000", "", "(ham, cheese, pineapple,lettuce, corn,tomato,cucumber)"});
            newPriceHashMap.put("Fish & Chips", new String[]{"15000", "", "(fried fish, lettuce, corn, chips, tartar dressing)"});
            newPriceHashMap.put("French Fries", new String[]{"6000", "", ""});
            newPriceHashMap.put("Extra Dressing", new String[]{"1000", "", ""});

            // Beverages
            newPriceHashMap.put("PEPSI", new String[]{"2000 ", "", ""});
            newPriceHashMap.put("7UP", new String[]{"2000 ", "", ""});
            newPriceHashMap.put("MIRINDA", new String[]{"2000 ", "", ""});
            newPriceHashMap.put("WATER SMALL", new String[]{"1000 ", "", ""});
            newPriceHashMap.put("WATER BIG", new String[]{"2000 ", "", ""});

        }


        if (AppGlobals.getCurrentSelectedStore().equals("tan bakji")) {

            //Salads

            newPriceHashMap.put("Season Salad", new String[]{"6000 ", "", ""});
            newPriceHashMap.put("Fattouch", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Tabboule", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Raheb Salad", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Zaatar, Rocca & Bakleh Salad", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Halloum Salad", new String[]{"9000 ", "", ""});
            //Mezza

            newPriceHashMap.put("Hommos ", new String[]{"6000 ", "", ""});
            newPriceHashMap.put("Hommos Beiruti", new String[]{"6000 ", "", ""});
            newPriceHashMap.put("Mutabal", new String[]{"6500 ", "", ""});
            newPriceHashMap.put("Mousakaa", new String[]{"8000 ", "", ""});
            newPriceHashMap.put("Labneh", new String[]{"5500 ", "", ""});
            newPriceHashMap.put("Goat Labneh", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Labneh with Garlic", new String[]{"6500 ", "", ""});
            newPriceHashMap.put("Tajen Fish", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Wark Enab", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Hendbeh Bel Zeit", new String[]{"6500 ", "", ""});
            newPriceHashMap.put("Basterma", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("Batrakh", new String[]{"26500 ", "", ""});
            newPriceHashMap.put("Halloum Grilled", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Kebbeh Halabieh", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Rkakat Cheese & Sujok", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("Rkakat Cheese", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Burak Cheese", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("Sambousek Lahme", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Foul", new String[]{"6500 ", "", ""});
            newPriceHashMap.put("Balila", new String[]{"6500 ", "", ""});
            newPriceHashMap.put("Hommos Snawbar", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Hommos Awarma", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("Hommos Makanek", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("Hommos Sujok", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("Hommos Meat & Snawbar", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("Eggs Sumak", new String[]{"5500 ", "", ""});
            newPriceHashMap.put("Eggs Awarma", new String[]{"9000 ", "", ""});
            newPriceHashMap.put("Eggs Sujok", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("Chicken Liver Tanbakji", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("Makanek", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("Sujok", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("Frogs", new String[]{"15000 ", "", ""});
            newPriceHashMap.put("French Fries", new String[]{"6500 ", "", ""});
            newPriceHashMap.put("Potato Harra", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("Potato Tanbakji", new String[]{"5500 ", "", ""});
            newPriceHashMap.put("Grilled Potatoes", new String[]{"6000 ", "", ""});
            newPriceHashMap.put("Grilled Wings", new String[]{"11500 ", "", ""});


            //Raw Meat

            newPriceHashMap.put("Mixed Raw Meat", new String[]{"31500 ", "", ""});
            newPriceHashMap.put("Raw Kebbeh with Chili", new String[]{"13000 ", "", ""});
            newPriceHashMap.put("Raw Kebbeh", new String[]{"13000 ", "", ""});
            newPriceHashMap.put("Raw Habra", new String[]{"12500 ", "", ""});
            newPriceHashMap.put("Raw Kafta", new String[]{"12000 ", "", ""});


                //TanbakjiSpecials

            newPriceHashMap.put("Falafel", new String[]{"10000 ", "", ""});
            newPriceHashMap.put("Sfiha Grilled", new String[]{"12500 ", "", ""});
            newPriceHashMap.put("Hommos Tanbakji", new String[]{"18500 ", "", ""});
            newPriceHashMap.put("Fatte Hommos", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Fatte Batenjan", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("Chawarma Chicken", new String[]{"18000 ", "", ""});
            newPriceHashMap.put("Chawarma Meat", new String[]{"19000 ", "", ""});
            newPriceHashMap.put("Kebbeh Jabalieh", new String[]{"16000 ", "", ""});
            newPriceHashMap.put("Grilled Kebbeh with Labneh", new String[]{"14500 ", "", ""});
            newPriceHashMap.put("Kebab with Yogurt", new String[]{"15500 ", "", ""});


            //Grill

            newPriceHashMap.put("Grilled Meat (Lamb)", new String[]{"18000 ", "", ""});
            newPriceHashMap.put("Grilled Meat (Beef)", new String[]{"17000 ", "", ""});
            newPriceHashMap.put("Kebab Halabi", new String[]{"14000 ", "", ""});
            newPriceHashMap.put("Kebab Batenjan", new String[]{"15500 ", "", ""});
            newPriceHashMap.put("Kebab Orfali", new String[]{"15000 ", "", ""});
            newPriceHashMap.put("Chich Taouk", new String[]{"15500 ", "", ""});
            newPriceHashMap.put("Mashawi Mixed", new String[]{"20000 ", "", ""});
            newPriceHashMap.put("Chicken Grilled Breast", new String[]{"22500 ", "", ""});
            newPriceHashMap.put("Grilled Fish", new String[]{"18000 ", "", ""});
            newPriceHashMap.put("Grilled Shrimps ", new String[]{"1 Kg)	55000 ", "", ""});


            //Desserts

            newPriceHashMap.put("Biscuit au Chocolat", new String[]{"5500 ", "", ""});
            newPriceHashMap.put("Mouhalabiye", new String[]{"4500 ", "", ""});
            newPriceHashMap.put("Crepes with Walnuts", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Layali Zahle", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("Ice Cream ", new String[]{"2000 ", "", ""});
            //Beverages

            newPriceHashMap.put("Mineral Water", new String[]{"1.5 L	2500 ", "", ""});
            newPriceHashMap.put("Mineral Water", new String[]{"0.5 L	1500 ", "", ""});
            newPriceHashMap.put("Perrier", new String[]{"5500 ", "", ""});
            newPriceHashMap.put("Soft Drinks", new String[]{"2500 ", "", ""});
            newPriceHashMap.put("Lemonade	", new String[]{"5000 ", "", ""});
            newPriceHashMap.put("Orange Juice", new String[]{"5500 ", "", ""});

                //Hot Beverages

            newPriceHashMap.put("Nescaf?", new String[]{"4500 ", "", ""});
            newPriceHashMap.put("Turkish Coffee", new String[]{"4500 ", "", ""});
            newPriceHashMap.put("White Coffee", new String[]{"4500 ", "", ""});
            newPriceHashMap.put("Tisane", new String[]{"4500 ", "", ""});

            //AlcoholicBeverages

            newPriceHashMap.put("Almaza", new String[]{"6000 ", "", ""});
            newPriceHashMap.put("Whisky Extra , btl", new String[]{"96000 ", "", ""});
            newPriceHashMap.put("Whisky Extra ,", new String[]{"0.5 btl	52000 ", "", ""});
            newPriceHashMap.put("Whisky Extra , gls", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("Whisky Regular , btl ", new String[]{"65000 ", "", ""});
            newPriceHashMap.put("Whisky Regular ,", new String[]{"0.5 btl 	35000 ", "", ""});
            newPriceHashMap.put("Whisky Regular , gls", new String[]{"10000 ", "", ""});
            newPriceHashMap.put("Stolichnaya Vodka , btl", new String[]{"50000 ", "", ""});
            newPriceHashMap.put("Stolichnaya Vodka , gls", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("Absolute Vodka , btl", new String[]{"56000 ", "", ""});
            newPriceHashMap.put("Absolute Vodka , gls", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("Arak (Brun , Samir , Ksara) , btl", new String[]{"45000 ", "", ""});
            newPriceHashMap.put("Arak (Brun , Samir , Ksara) ,", new String[]{"0.5 btl	30000 ", "", ""});
            newPriceHashMap.put("Arak (Brun , Samir , Ksara) ,", new String[]{"0.25 btl	19000 ", "", ""});
            newPriceHashMap.put("Arak (Brun , Samir , Ksara) , gls", new String[]{"10000 ", "", ""});
            newPriceHashMap.put("Arak Al karram , btl ", new String[]{"40000 ", "", ""});
            newPriceHashMap.put("Arak Al karram ,", new String[]{"0.5 btl	28000 ", "", ""});
            newPriceHashMap.put("Arak Al karram ,", new String[]{"0.25 btl	15000 ", "", ""});
            newPriceHashMap.put("Arak Al karram , gls", new String[]{"9000 ", "", ""});


            //Wine

            newPriceHashMap.put("Chateau Ksara , btl ", new String[]{"63000 ", "", ""});
            newPriceHashMap.put("Ksara Reserve du couvent , btl ", new String[]{"40000 ", "", ""});
            newPriceHashMap.put("Ksara Reserve du couvent ,", new String[]{"0.5 btl 	19500 ", "", ""});
            newPriceHashMap.put("Ksara Blanc de Blanc , btl", new String[]{"40000 ", "", ""});
            newPriceHashMap.put("Ksara Blanc de Blanc ,", new String[]{"0.5 btl	19500 ", "", ""});
            newPriceHashMap.put("Ksara Sunset , btl ", new String[]{"40000 ", "", ""});
            newPriceHashMap.put("Ksara Sunset ,", new String[]{"0.5 btl	19500 ", "", ""});
            newPriceHashMap.put("Domaine des Tourelles , btl", new String[]{"34500 ", "", ""});
            newPriceHashMap.put("Nakad , btl", new String[]{"30000 ", "", ""});
            newPriceHashMap.put("Warde Chateau les c?dres , btl ", new String[]{"75000 ", "", ""});


            //Narguile

            newPriceHashMap.put("Mouassal", new String[]{"12500 ", "", ""});
            newPriceHashMap.put("Ajami", new String[]{"14000 ", "", ""});

        }
        if (AppGlobals.getCurrentSelectedStore().equals("adokit")) {




            /// Mouneh :
            newPriceHashMap.put("Kebbeh Krass 6 pcs", new String[]{"14000", "", ""});
            newPriceHashMap.put("Kebbeh halabiyeh 12 pcs", new String[]{"12000", "", ""});
            newPriceHashMap.put("Kebbeh Zghertawiye 6 pcs", new String[]{"12000", "", ""});
            newPriceHashMap.put("Kebbeh Awarma and Labaneh 6 pcs", new String[]{"20000", "", ""});
            newPriceHashMap.put("Kebbeh samak", new String[]{"12000", "", ""});
            newPriceHashMap.put("Kebbeh djej", new String[]{"12000", "", ""});
            newPriceHashMap.put("Kebbeh Are3(pumpkin) 12 pcs", new String[]{"24000", "", ""});
            // fried mouneh
            newPriceHashMap.put("Fried Kebbeh Krass 6 pcs", new String[]{"14000", "", ""});
            newPriceHashMap.put("Fried Kebbeh halabiyeh 12 pcs", new String[]{"12000", "", ""});
            newPriceHashMap.put("Fried Kebbeh Zghertawiye 6 pcs", new String[]{"12000", "", ""});
            newPriceHashMap.put("Fried Kebbeh Awarma and Labaneh 6 pcs", new String[]{"20000", "", ""});
            newPriceHashMap.put("Fried Kebbeh samak", new String[]{"12000", "", ""});
            newPriceHashMap.put("Fried Kebbeh djej", new String[]{"12000", "", ""});
            newPriceHashMap.put("Fried Kebbeh Are3(pumpkin) 12 pcs", new String[]{"24000", "", ""});

            // Healthy Retail Groceries
            newPriceHashMap.put("Sambousik", new String[]{"24000", "", ""});
            newPriceHashMap.put("Rkakat", new String[]{"8000", "", ""});
            newPriceHashMap.put("Makanek (sausages) 500G", new String[]{"15000", "", ""});
            newPriceHashMap.put("Soujok 500G", new String[]{"12500", "", ""});
            newPriceHashMap.put("Keshek 500G", new String[]{"18000", "", ""});
            newPriceHashMap.put("Awarma 500G", new String[]{"20000", "", ""});
            newPriceHashMap.put("Goat Labaneh 500g", new String[]{"9000", "", ""});
            newPriceHashMap.put("Warak 3enab 500g", new String[]{"10000", "", ""});
            newPriceHashMap.put("Foor cheese 8 pcs", new String[]{"12000", "", ""});
            newPriceHashMap.put("Pizza 8 pcs", new String[]{"12000", "", ""});
            newPriceHashMap.put("Chicek cheese 8 pcs", new String[]{"12000", "", ""});
            newPriceHashMap.put("Cheddar Hotdog 8 pcs", new String[]{"12000", "", ""});
            newPriceHashMap.put("Olie-Thym cheese 8 pcs", new String[]{"12000", "", ""});
            newPriceHashMap.put("Banana cinammon (caramelized) 8 pcs", new String[]{"12000", "", ""});
            newPriceHashMap.put("Straw-cranberry dark chocolate Combo(8 mixed pc)", new String[]{"12000", "", ""});


        }

        // chouiery started
        if (AppGlobals.getCurrentSelectedStore().equals("Choueiry")) {
            /// Akel :
            newPriceHashMap.put("Sandwich Djej", new String[]{"4500", "", ""});
            newPriceHashMap.put("Sandwich 2asabit djej", new String[]{"4500", "", ""});
            newPriceHashMap.put("Fakhd djej", new String[]{"4500", "", ""});
            newPriceHashMap.put("Sfinet djej", new String[]{"5000", "", ""});
            newPriceHashMap.put("Noss Farrouj", new String[]{"8000", "", ""});
            newPriceHashMap.put("Farrouj kemil", new String[]{"15000", "", ""});

            newPriceHashMap.put("Toum Kbir", new String[]{"2000", "", ""});
            newPriceHashMap.put("Toum Zghir", new String[]{"500", "", ""});

            newPriceHashMap.put("Hoummoss kbir", new String[]{"3000", "", ""});
            newPriceHashMap.put("Hoummoss zghir", new String[]{"2000", "", ""});

            // Beverges
            newPriceHashMap.put("Pepsi tanak", new String[]{"1000", "", ""});
            newPriceHashMap.put("Mirinda tanak", new String[]{"1000", "", ""});
            newPriceHashMap.put("7up tanak", new String[]{"1000", "", ""});
            newPriceHashMap.put("May zghir", new String[]{"500", "", ""});
            newPriceHashMap.put("May kbir", new String[]{"1000", "", ""});




        }

        // falafelelbeyt started

        if (AppGlobals.getCurrentSelectedStore().equals("falafel el beyt")) {

            newPriceHashMap.put("Sandwich Falefil", new String[]{"2500", "", ""});
            newPriceHashMap.put("Falefil bi Khebez mar2ou2", new String[]{"3000", "", ""});
            newPriceHashMap.put("Dazinet Falefil bala khodra", new String[]{"6000", "", ""});
            newPriceHashMap.put("Dazinet Falefil ma3 khodra", new String[]{"12000", "", ""});
            newPriceHashMap.put("3elbit tarator", new String[]{"2000", "", ""});

            // Beverges
            newPriceHashMap.put("Pepsi", new String[]{"1000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"1000", "", ""});
            newPriceHashMap.put("7up", new String[]{"1000", "", ""});
        }

        if (AppGlobals.getCurrentSelectedStore().equals("mrgrill")) {

            //Salads
            newPriceHashMap.put("Tabouleh", new String[]{"8000", "", ""});
            newPriceHashMap.put("Fattouch", new String[]{"8000", "", ""});
            newPriceHashMap.put("Mawsam Salad", new String[]{"7500", "", ""});
            newPriceHashMap.put("Zaatar  Bakli  Rocca", new String[]{"8000", "", ""});
            newPriceHashMap.put("Chmandar", new String[]{"7000", "", ""});
            newPriceHashMap.put("khodra kabiss zaytoun", new String[]{"7500", "", ""});
            newPriceHashMap.put("Jat khodra Mouchakal", new String[]{"15000", "", ""});
            newPriceHashMap.put("Avocado Salad", new String[]{"10000", "", ""});
            newPriceHashMap.put("Al Raheb Salad", new String[]{"7500", "", ""});
//
//Cold Mezza
            newPriceHashMap.put("Hommos", new String[]{"6500", "", ""});
            newPriceHashMap.put("Hommos Beiruty", new String[]{"7000", "", ""});
            newPriceHashMap.put("Hommos Mr Grill", new String[]{"7000", "", ""});
            newPriceHashMap.put("Mtabal", new String[]{"6500", "", ""});
            newPriceHashMap.put("Labaneh", new String[]{"6500", "", ""});
            newPriceHashMap.put("Labaneh Bil Toum", new String[]{"6500", "", ""});
            newPriceHashMap.put("              Labaneh maez", new String[]{"8000", "", ""});
            newPriceHashMap.put("Labaneh maez extra", new String[]{"9000", "", ""});
            newPriceHashMap.put("Shanklish", new String[]{"6500", "", ""});
            newPriceHashMap.put("Tajen Samak", new String[]{"8500", "", ""});
            newPriceHashMap.put("            Basterma Arayes", new String[]{"8500", "", ""});
            newPriceHashMap.put("Warak Enab", new String[]{"7500", "", ""});
            newPriceHashMap.put("Ardi Chawki", new String[]{"6500", "", ""});
            newPriceHashMap.put("Hendbeh", new String[]{"7000", "", ""});
            newPriceHashMap.put("Batrakh", new String[]{"29000", "", ""});
            newPriceHashMap.put("Batata Mr Grill", new String[]{"6500", "", ""});
            newPriceHashMap.put("Batata Mishwieh", new String[]{"6000", "", ""});
            newPriceHashMap.put("Mkassarat", new String[]{"4000", "", ""});
            newPriceHashMap.put("   Mkassarat Extra", new String[]{"6000", "", ""});
            newPriceHashMap.put("Hommos Snawbar", new String[]{"7000", "", ""});
            newPriceHashMap.put("Loubieh Bi zeit", new String[]{"9500", "", ""});
            newPriceHashMap.put("Msakaa Batenjane", new String[]{"5500", "", ""});
//SERVICE CHARGE  VAT INCLUDED
// MENU MR GRILL
//Hot Mezza
            newPriceHashMap.put("Halloum Fried Grilled", new String[]{"10000", "", ""});
            newPriceHashMap.put("Batata Meklieh", new String[]{"6000", "", ""});
            newPriceHashMap.put("Batata Harra", new String[]{"8500", "", ""});
            newPriceHashMap.put(" Falafel", new String[]{"9000", "", ""});
            newPriceHashMap.put("Hommos  Awarma", new String[]{"9000", "", ""});
            newPriceHashMap.put("Hommos  Lahmeh  Snawbar", new String[]{"10000", "", ""});
            newPriceHashMap.put("Balila", new String[]{"6500", "", ""});
            newPriceHashMap.put("Foul", new String[]{"6500", "", ""});
            newPriceHashMap.put("Beyd Awarma", new String[]{"9000", "", ""});
            newPriceHashMap.put("Makanek", new String[]{"10000", "", ""});
            newPriceHashMap.put("Soujouk", new String[]{"10000", "", ""});
            newPriceHashMap.put("Beyd Ghanam", new String[]{"15000", "", ""});
            newPriceHashMap.put("                    Sawda Djej", new String[]{"10000", "", ""});
            newPriceHashMap.put("Dafadeh", new String[]{"16000", "", ""});
            newPriceHashMap.put("Batenjane Mekleh", new String[]{"5500", "", ""});
            newPriceHashMap.put("Kousa Mekleh", new String[]{"4500", "", ""});
            newPriceHashMap.put("Karnabeet Mekleh", new String[]{"4500", "", ""});
            newPriceHashMap.put("Sahn Makaleh Mchakal", new String[]{"5500", "", ""});
//
//Mouajanat
//
            newPriceHashMap.put("Kebbeh Halabieh", new String[]{"8500", "", ""});
            newPriceHashMap.put("Samboussik Lahmeh", new String[]{"8000", "", ""});
            newPriceHashMap.put("Rakakat Jebneh Fried or Grilled", new String[]{"8000", "", ""});
            newPriceHashMap.put("Mouajanat Mouchakal", new String[]{"9500", "", ""});
            newPriceHashMap.put("Kebbeh Areh", new String[]{"7500", "", ""});
//
//Raw Meat
//
            newPriceHashMap.put("Habrah Nayeh", new String[]{"15000", "", ""});
            newPriceHashMap.put("Kebbeh Nayeh Regular or Hot", new String[]{"15000", "", ""});
            newPriceHashMap.put("Kafta Nayeh", new String[]{"15000", "", ""});
            newPriceHashMap.put("Sawda", new String[]{"16000", "", ""});
            newPriceHashMap.put("Nayeh Mouchakal", new String[]{"35000", "", ""});
            newPriceHashMap.put("Kebbeh Orfali", new String[]{"16000", "", ""});
//
//
//  SERVICE CHARGE  VAT INCLUDED
// MENU MR GRILL
//
//Al Mashawi
//
            newPriceHashMap.put("Kabab Mr Grill Hot", new String[]{"15000", "", ""});
            newPriceHashMap.put("Kafta", new String[]{"15000", "", ""});
            newPriceHashMap.put("Kabab Halabi", new String[]{"15000", "", ""});
            newPriceHashMap.put("kabab Orfali Regular or Hot", new String[]{"15000", "", ""});
            newPriceHashMap.put("kabab Bazinjan", new String[]{"16000", "", ""});
            newPriceHashMap.put("Kabab Khachkhach", new String[]{"15000", "", ""});
            newPriceHashMap.put("Kabab Istambouli", new String[]{"15000", "", ""});
            newPriceHashMap.put("Kebbeh on the Grill", new String[]{"15000", "", ""});
            newPriceHashMap.put("Lahm Meshwe Lamb", new String[]{"19000", "", ""});
            newPriceHashMap.put("Lahm Meshwe Veal", new String[]{"17000", "", ""});
            newPriceHashMap.put("Lamb Chops", new String[]{"19000", "", ""});
            newPriceHashMap.put("Makanek", new String[]{"10000", "", ""});
            newPriceHashMap.put("Soujouk", new String[]{"10000", "", ""});
            newPriceHashMap.put("Farrouj Msahhab", new String[]{"21000", "", ""});
            newPriceHashMap.put("Taouk", new String[]{"16000", "", ""});
            newPriceHashMap.put("Wings", new String[]{"10000", "", ""});
            newPriceHashMap.put("Arayes Kafta", new String[]{"13000", "", ""});
            newPriceHashMap.put("Mashawi Mchakal", new String[]{"20000", "", ""});
            newPriceHashMap.put("Mashawi Mchakal kilo", new String[]{"67000", "", ""});
            newPriceHashMap.put("Lahm Meshwe Lamb kilo", new String[]{"69000", "", ""});
            newPriceHashMap.put("     Lahm Meshwe Veal kilo", new String[]{"62000", "", ""});
            newPriceHashMap.put("Kabab kilo", new String[]{"62000", "", ""});
            newPriceHashMap.put("Taouk kilo", new String[]{"60000", "", ""});
            newPriceHashMap.put("Grilled Hammour Fillet", new String[]{"18000", "", ""});
            newPriceHashMap.put("Jumbo Prawns", new String[]{"34000", "", ""});
            newPriceHashMap.put("Jumbo Prawns KG", new String[]{"840001", "", ""});
            newPriceHashMap.put("Assafir dozen", new String[]{"45000", "", ""});
//
//
//
            //  SERVICE CHARGE  VAT INCLUDED
                // MENU MR GRILL
            //MANAKISH MAFTOUHA AW ARAYES
//
            newPriceHashMap.put("Zaatar balady", new String[]{"3000", "", ""});
            newPriceHashMap.put("Zaatar balady with tomatocucumberolives", new String[]{"3500", "", ""});
            newPriceHashMap.put("Labaneh with zaatar", new String[]{"4500", "", ""});
            newPriceHashMap.put("Halloum", new String[]{"6000", "", ""});
            newPriceHashMap.put("Kashkawan cheese", new String[]{"6500", "", ""});
            newPriceHashMap.put("Kashkawan with zaatar", new String[]{"6500", "", ""});
            newPriceHashMap.put("Lahm baajin with or without spices", new String[]{"6000", "", ""});
//
                //DAILY PLAT
//
            newPriceHashMap.put(" Kebbe b sayniyesalad or mloukhiyetuesday", new String[]{"20000", "", ""});
            newPriceHashMap.put("Friki  Wednesday", new String[]{"20000", "", ""});
            newPriceHashMap.put("   Warak enab kusa fawarigh kraain thursday", new String[]{"280003", "", ""});
            newPriceHashMap.put("Siyadiyet samak loukouz ramli friday", new String[]{"30000", "", ""});
//
            newPriceHashMap.put("Tray Mr Grill", new String[]{"30000", "", ""});
//
//
            //Cocktails  Juices
//
            newPriceHashMap.put("Cocktail ", new String[]{"11000", "", ""});
            newPriceHashMap.put("Cocktail juice ", new String[]{"8500", "", ""});
            newPriceHashMap.put("Avocado", new String[]{"8000", "", ""});
            newPriceHashMap.put("Kiwi ", new String[]{"7500", "", ""});
            newPriceHashMap.put("Mango", new String[]{"6500", "", ""});
            newPriceHashMap.put("Pineapple", new String[]{"7500", "", ""});
            newPriceHashMap.put("Strawberry", new String[]{"5000", "", ""});
            newPriceHashMap.put("Orange", new String[]{"6000", "", ""});
            newPriceHashMap.put("Lemonade", new String[]{"4500", "", ""});
            newPriceHashMap.put("Mint Lemonade", new String[]{"5000", "", ""});
//
            //Ice Cream
            newPriceHashMap.put("Ice Cream cup", new String[]{"9000", "", ""});
//
                //Desserts
//
            newPriceHashMap.put("Mouhalabieh", new String[]{"5000", "", ""});
            newPriceHashMap.put("Oriental Sweets", new String[]{"6000", "", ""});
//
            //  SERVICE CHARGE  VAT INCLUDED
            // MENU MR GRILL
//
            //Milk Shakes
            newPriceHashMap.put("Banana Milkshake", new String[]{"6000", "", ""});
            newPriceHashMap.put("Chocolate Banana Milkshake", new String[]{"6000", "", ""});
            newPriceHashMap.put("Strawberry Milkshake", new String[]{"6000", "", ""});
//
//Special cocktail
            newPriceHashMap.put("Turbo ", new String[]{"9500", "", ""});
            newPriceHashMap.put("Olympic", new String[]{"11500", "", ""});
            newPriceHashMap.put("Tahiti", new String[]{"9500", "", ""});
            newPriceHashMap.put("Fruit salad", new String[]{"9000", "", ""});
            newPriceHashMap.put("Exotic plates", new String[]{"15000", "", ""});
//
            //Drinks
//
            newPriceHashMap.put("Red Bull", new String[]{"6000", "", ""});
            newPriceHashMap.put("Mineral Water Small", new String[]{"1500", "", ""});
            newPriceHashMap.put("Mineral Water Large", new String[]{"2500", "", ""});
            newPriceHashMap.put("Soft Drinks", new String[]{"3000", "", ""});
            newPriceHashMap.put("Perrier", new String[]{"5000", "", ""});
            newPriceHashMap.put("laziza", new String[]{"5000", "", ""});
            newPriceHashMap.put("Local Beer", new String[]{"6500", "", ""});
            newPriceHashMap.put("Almaza light", new String[]{"7000", "", ""});
//
//
//
            //Sandwiches
            newPriceHashMap.put("Kafta", new String[]{"6500", "", ""});
            newPriceHashMap.put("Kabab Halabi", new String[]{"6500", "", ""});
            newPriceHashMap.put("Kabab Istambouli", new String[]{"6500", "", ""});
            newPriceHashMap.put("Kabab Orfali", new String[]{"6500", "", ""});
            newPriceHashMap.put("kabab Khachkhach", new String[]{"6500", "", ""});
            newPriceHashMap.put("kabab Bazinjan", new String[]{"6500", "", ""});
            newPriceHashMap.put("Kebbeh on The Grill", new String[]{"6500", "", ""});
            newPriceHashMap.put("Lahm Meshwe Lamb", new String[]{"8000", "", ""});
            newPriceHashMap.put("Lahm Meshwe Veal", new String[]{"7000", "", ""});
            newPriceHashMap.put("Makanek", new String[]{"6000", "", ""});
            newPriceHashMap.put("Soujouk", new String[]{"6000", "", ""});
            newPriceHashMap.put("Batata", new String[]{"4000", "", ""});
            newPriceHashMap.put("Falafel", new String[]{"4000", "", ""});
            newPriceHashMap.put("Labaneh", new String[]{"4000", "", ""});


        }
        if (AppGlobals.getCurrentSelectedStore().equals("hollywood")) {
            //SPEICAL WRAP MAKI (4PCs)
            newPriceHashMap.put("CRAZY CALIFORNIA", new String[]{"7000 ", "", ""});
            newPriceHashMap.put("SPICY SALMON", new String[]{"8000 ", "", ""});
            newPriceHashMap.put("SALMON", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("MANGO", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("CALIFORNIA", new String[]{"7000 ", "", ""});
            newPriceHashMap.put("CANADIAN MAKI", new String[]{"8500 ", "", ""});
//MAIN COURSES
            newPriceHashMap.put("CREAM OF MUSHROOM", new String[]{"9000 ", "", ""});
            newPriceHashMap.put("SOUP OF THE DAY", new String[]{"9000 ", "", ""});
//SALTED CREPES
            newPriceHashMap.put("GRILLED �HAMOUR� FILLET", new String[]{"18500 ", "", ""});
            newPriceHashMap.put("GRILLED SALMON FILLET", new String[]{"26000 ", "", ""});
            newPriceHashMap.put("MIXED FRIED SEAFOOD", new String[]{"22000 ", "", ""});
            newPriceHashMap.put("HOLLYWOOD CAF� GRILLED PRAWNS", new String[]{"36000 ", "", ""});
            newPriceHashMap.put("CRISPY TEMPURA FRIED PRAWNS", new String[]{"35000 ", "", ""});
            newPriceHashMap.put("SHRIMPS WITH CURRY SAUCE", new String[]{"25000 ", "", ""});
            newPriceHashMap.put("CHIKEN MUSTARD SAUCE", new String[]{"20000 ", "", ""});
            newPriceHashMap.put("CHICKEN RAGOUT", new String[]{"20000 ", "", ""});
            newPriceHashMap.put("CHICKEN ESCALOPE", new String[]{"18500 ", "", ""});
            newPriceHashMap.put("CHICKEN CORDN BLEU", new String[]{"22000 ", "", ""});
            newPriceHashMap.put("ELEGANT CHICKEN", new String[]{"26000 ", "", ""});
            newPriceHashMap.put("INDIAN CHIKEN CURRY", new String[]{"20000 ", "", ""});
            newPriceHashMap.put("PEPPER CHICKEN STEAK", new String[]{"22000 ", "", ""});
            newPriceHashMap.put("CHIKEN FAJITA", new String[]{"22000 ", "", ""});
            newPriceHashMap.put("ESCALLOPE VIENNOISE", new String[]{"23000 ", "", ""});
            newPriceHashMap.put("STEAK ROBERT", new String[]{"30000 ", "", ""});
            newPriceHashMap.put("STEAK AU POIVRE", new String[]{"30000 ", "", ""});
            newPriceHashMap.put("STEAK ROQUEFORT", new String[]{"31000 ", "", ""});
            newPriceHashMap.put("CHATEAUBRIAND", new String[]{"32000 ", "", ""});
            newPriceHashMap.put("GRILLED FILLET ROSSINI", new String[]{"30000 ", "", ""});
            newPriceHashMap.put("BEEF STROGSNOFF", new String[]{"23000 ", "", ""});
//TEKAMI
            newPriceHashMap.put("CRISPY SALMON", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("CRISPY TUNA", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("SPICY URA SALMON", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("KANI SALMON", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("HANAMI SALMON", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("CRISPY SCALLOPS", new String[]{"10000 ", "", ""});
            newPriceHashMap.put("CRISPY CALIFORNIA", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("CRISPY EEL", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("CRISPY SHRIMP", new String[]{"8000 ", "", ""});
            newPriceHashMap.put("CRISPY UNA", new String[]{"13000 ", "", ""});
            newPriceHashMap.put("TEMPURA ROLL", new String[]{"8000 ", "", ""});
//STARTERS & APPETIZERS
            newPriceHashMap.put("TUNA", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("FISH BURGER", new String[]{"8000 ", "", ""});
            newPriceHashMap.put("CHICH TAOUK", new String[]{"8000 ", "", ""});
            newPriceHashMap.put("CHICKEN BURGER", new String[]{"8000 ", "", ""});
            newPriceHashMap.put("HAMBURGER", new String[]{"9000 ", "", ""});
            newPriceHashMap.put("SPICY BURGER", new String[]{"9000 ", "", ""});
            newPriceHashMap.put("CHEESE BURGER", new String[]{"9000 ", "", ""});
            newPriceHashMap.put("BACON CHEESE BURGER", new String[]{"10000 ", "", ""});
//TOBICO URA MAKI
            newPriceHashMap.put("SALMON", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("TUNA", new String[]{"7000 ", "", ""});
            newPriceHashMap.put("SCALLOPS", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("SHRIMP", new String[]{"7000 ", "", ""});
            newPriceHashMap.put("CRAB", new String[]{"6000 ", "", ""});
            newPriceHashMap.put("EEL", new String[]{"8000 ", "", ""});
            newPriceHashMap.put("SPICY SALMON", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("CRISPY SALMON", new String[]{"7500 ", "", ""});
//LEBANESE FOOD
            newPriceHashMap.put("SPAGHETTI WITH TOMATO SAUCE & CHEESE", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("SPAGHETTI WITH WHITE SAUCE & CHEESE", new String[]{"14000 ", "", ""});
            newPriceHashMap.put("CURTIS PRAWNS IN TAGLIATELLE", new String[]{"330000 ", "", ""});
            newPriceHashMap.put("SPICY SPAGHETTI WITH SHRIMPS", new String[]{"22000 ", "", ""});
            newPriceHashMap.put("TAGLIATELLE SALMON", new String[]{"20000 ", "", ""});
//PASTA
            newPriceHashMap.put("STEAMED RICE", new String[]{"5500 ", "", ""});
            newPriceHashMap.put("FRIED RICE WITH CORN", new String[]{"6500 ", "", ""});
//LIGHT MENU
            newPriceHashMap.put("SESAMI BUTTER FRIED CHICKEN", new String[]{"24000 ", "", ""});
            newPriceHashMap.put("BRAISED FILLET OF BEEF", new String[]{"30000 ", "", ""});
            newPriceHashMap.put("BEEF TERMIDOS CINZANO SAUCE ", new String[]{"31000 ", "", ""});
            newPriceHashMap.put("MINI STEAK IN BACON MUSHROOM & ONION", new String[]{"31000 ", "", ""});
            newPriceHashMap.put("AMERICAN T-BONE STEAK (450G)", new String[]{"49000 ", "", ""});
            newPriceHashMap.put("AMERICAN TENDERLOIN STEAK", new String[]{"43000 ", "", ""});
            newPriceHashMap.put("AUSTRALIAN TENDERLOIN", new String[]{"38000 ", "", ""});
//SUSHI
            newPriceHashMap.put("SALAMON", new String[]{"10000 ", "", ""});
            newPriceHashMap.put("TUNA", new String[]{"10500 ", "", ""});
            newPriceHashMap.put("EEL", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("SCALLOPS", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("HAMACHI", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("WHITE FISH", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("SHRIMPS", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("CRAB", new String[]{"12000 ", "", ""});
//SESAME URA MAKL(3PCS)
            newPriceHashMap.put("SPECIAL CRAZY", new String[]{"9000 ", "", ""});
            newPriceHashMap.put("SPECIAL CALIFORNIA", new String[]{"8000 ", "", ""});
            newPriceHashMap.put("SHRIMP TOBICO", new String[]{"8000 ", "", ""});
            newPriceHashMap.put("PHILI-SALMON", new String[]{"9000 ", "", ""});
//SANDWICH PLATTERS
            newPriceHashMap.put("SLICES BAKED POTATO", new String[]{"6000 ", "", ""});
            newPriceHashMap.put("SLICED BAKED POTATO WITH CHEESE", new String[]{"8500 ", "", ""});
            newPriceHashMap.put("FRENCH FRIES", new String[]{"6000 ", "", ""});
            newPriceHashMap.put("FRENCH FRIES WITH CHEDDAR CHEESE", new String[]{"9000 ", "", ""});
            newPriceHashMap.put( "FRIED POTATO WEDGES", new String[]{"8500 ", "", ""});
            newPriceHashMap.put( "TWISTER FRENCH FRIES", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("TWISTER FRENCH FRIES WITH CHEDDAR CHEESE", new String[]{"11500 ", "", ""});
            newPriceHashMap.put( "CROQUETTE POTATO", new String[]{"6500 ", "", ""});
            newPriceHashMap.put("GARLIC CHEESE BREAD (5PCS)", new String[]{"8000 ", "", ""});
            newPriceHashMap.put( "FRIED MOZZARELLA STICKS WITH CHILLY SAUCE (6 PCS)", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("CHEESE OMELETTE", new String[]{"8000 ", "", ""});
            newPriceHashMap.put("MIXED FRIED SEAFOOD", new String[]{"10000 ", "", ""});
            newPriceHashMap.put("SHRIMP COCKTAIL", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("SMOKED SCOTTISH SALMON", new String[]{"25000 ", "", ""});
            newPriceHashMap.put("CHICKEN NUGGETS (5PCS)", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("BRESAOLA", new String[]{"17000 ", "", ""});
//CHINES FOOD
            newPriceHashMap.put("CREATE YOUR OWN CREPE", new String[]{"10000 ", "", ""});
//HOLLYWOOD CAFE'S SPECIALITIES
            newPriceHashMap.put("NAPOLITANA PIZZA", new String[]{"12500 ", "", ""});
            newPriceHashMap.put("PROSCIUTTO PIZZA", new String[]{"13500 ", "", ""});
            newPriceHashMap.put("TUNA PIZZA", new String[]{"14500 ", "", ""});
            newPriceHashMap.put("EXOTIC PIZZA", new String[]{"15500 ", "", ""});
            newPriceHashMap.put("SPICY HOT PIZZA", new String[]{"14500 ", "", ""});
            newPriceHashMap.put("HOLLYWOOD CAF� PIZZA", new String[]{"17000 ", "", ""});
//SOUP
            newPriceHashMap.put("CROQUE MONSIEUR", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("THE CLUB", new String[]{"15000 ", "", ""});
            newPriceHashMap.put("DOUBLE FISH BURGER", new String[]{"16000 ", "", ""});
            newPriceHashMap.put("DOUBLE CHICKEN BURRGER", new String[]{"16000 ", "", ""});
            newPriceHashMap.put("STEAK SANDWICH", new String[]{"17500 ", "", ""});
            newPriceHashMap.put("TORNADO", new String[]{"19000 ", "", ""});
            newPriceHashMap.put("DOUBLE HAMBURGER", new String[]{"18000 ", "", ""});
            newPriceHashMap.put("DOUBLE CHEESE BURGER", new String[]{"19000 ", "", ""});
            newPriceHashMap.put("DOUBLE BACON CHEESE BURGER", new String[]{"19000 ", "", ""});
//SPECIAL SETS
            newPriceHashMap.put("CRISPY SALMON", new String[]{"10000 ", "", ""});
            newPriceHashMap.put("CRISPY TUNA", new String[]{"10000 ", "", ""});
            newPriceHashMap.put("CRISPY CRAB", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("IKURA SALMON", new String[]{"10000 ", "", ""});
            newPriceHashMap.put("SAKE", new String[]{"20000 ", "", ""});
//HOSOMAKI
            newPriceHashMap.put("SALMON", new String[]{"10000 ", "", ""});
            newPriceHashMap.put("SHRIMP", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("WHITE FISH", new String[]{"11000 ", "", ""});
            newPriceHashMap.put("SCALLOPS", new String[]{"13000 ", "", ""});
            newPriceHashMap.put("TUNA", new String[]{"11000 ", "", ""});
            newPriceHashMap.put("EEL", new String[]{"14000 ", "", ""});
            newPriceHashMap.put("CRAB", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("SHRIMP TEMPURA", new String[]{"10000 ", "", ""});
//SALAD CORNER
            newPriceHashMap.put("VEGETABLE SPRING ROLLS", new String[]{"8000 ", "", ""});
            newPriceHashMap.put("VEGETERIAN SPAGHETTI", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("FISH FILLET WITH GINGER SOYA SAUCE", new String[]{"16000 ", "", ""});
            newPriceHashMap.put("FISH FILLET WITH BLACK BEANS SAUCE", new String[]{"16000 ", "", ""});
            newPriceHashMap.put("SWEET AND SOUR SHRIMPS", new String[]{"19500 ", "", ""});
            newPriceHashMap.put("CURRY SHRIMPS IN HOT POT", new String[]{"19500 ", "", ""});
            newPriceHashMap.put("SHRIMPS WITH GINGER AND ONION", new String[]{"19500 ", "", ""});
            newPriceHashMap.put("NEEHUOON NOODLES CHICKEN & SHRIMPS", new String[]{"21000 ", "", ""});
            newPriceHashMap.put("CANTON FRIED CRISPY NOODLES CHICKEN & SHRIMPS", new String[]{"24000 ", "", ""});
            newPriceHashMap.put("SWEET AND SOUR CHICKEN", new String[]{"16000 ", "", ""});
            newPriceHashMap.put("CHILI CHICKEN WITH CASHEW NUTS", new String[]{"17000 ", "", ""});
            newPriceHashMap.put("CHICKEN IN SATAY SAUCE", new String[]{"17000 ", "", ""});
            newPriceHashMap.put("CHICKEN WITH MIXED VEGETABLES", new String[]{"16000 ", "", ""});
            newPriceHashMap.put("CURRY CHICKEN IN HOT POT", new String[]{"16000 ", "", ""});
            newPriceHashMap.put("CHICKEN IN GINGER HONEY SAUCE", new String[]{"19000 ", "", ""});
            newPriceHashMap.put("CURRY BEEF IN HOT POT", new String[]{"19000 ", "", ""});
            newPriceHashMap.put("BEEF IN OYSTER SAUCE", new String[]{"19000 ", "", ""});
            newPriceHashMap.put("BEEF WITH GREEN PEPPER IN BLACK BEANS SAUCE", new String[]{"19000 ", "", ""});
            newPriceHashMap.put("BEEF CASHEW IN HOT POT", new String[]{"20000 ", "", ""});
            newPriceHashMap.put("SHANGHAI BEEF NOODLES", new String[]{"23000 ", "", ""});
//SASHIMI 3 PCs
            newPriceHashMap.put("EDAMAME", new String[]{"8000 ", "", ""});
            newPriceHashMap.put("CRISPY SPICY SALMON CRAB SALAD", new String[]{"22000 ", "", ""});
            newPriceHashMap.put("CRISPY SALMO N SALAD", new String[]{"22000 ", "", ""});
            newPriceHashMap.put("TUNA SALAD", new String[]{"20000 ", "", ""});
            newPriceHashMap.put("SALMON SALAD", new String[]{"20000 ", "", ""});
            newPriceHashMap.put("CALAMARI SALAD", new String[]{"22000 ", "", ""});
//CRISPY URA MAKI (3PCs)
            newPriceHashMap.put("SPANISH ROLL", new String[]{"11000 ", "", ""});
            newPriceHashMap.put("URA SHRIMP", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("FLYING SALMON", new String[]{"11500 ", "", ""});
            newPriceHashMap.put("SPECIAL EEL", new String[]{"12500 ", "", ""});
            newPriceHashMap.put("SPECIAL URA", new String[]{"11500 ", "", ""});
            newPriceHashMap.put("DEEP MANGO", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("SKIN DEEP", new String[]{"9000 ", "", ""});
            newPriceHashMap.put("KANI MANGO", new String[]{"11000 ", "", ""});
            newPriceHashMap.put("CINEMA ROLL", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("OH MY GOD", new String[]{"12000 ", "", ""});
//PIZZAS
            newPriceHashMap.put("FATTOUCH", new String[]{"8000 ", "", ""});
            newPriceHashMap.put("CHICH TAOUK", new String[]{"16000 ", "", ""});
//SUCHI SALAD
            newPriceHashMap.put("ROCCA N�ROLL CHICKEN", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("QUINOA SALAD", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("LIGHT TUNA PASTA SALAD", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("TOFU SALAD", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("LIGHT HAMOUR", new String[]{"19500 ", "", ""});
            newPriceHashMap.put("HOLLYWOOD SPECIAL DIET PIZZA", new String[]{"15000 ", "", ""});
            newPriceHashMap.put("FUSILLI ALA ROMANO", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("LIGHT STEAK AU POIVRE", new String[]{"28000 ", "", ""});
            newPriceHashMap.put("D-LIGHT CHICKEN", new String[]{"20000 ", "", ""});
            newPriceHashMap.put("BREAST CHICKEN WITH RED WINE", new String[]{"20000 ", "", ""});
            newPriceHashMap.put("STEAK MARINADE", new String[]{"27000 ", "", ""});
            newPriceHashMap.put("SMOKED TURKEY", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("EVER SLIM CHICKEN BURGER", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("FIT N' BURGER", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("CHICKEN TORTILLAS", new String[]{"12000 ", "", ""});
//RICE
            newPriceHashMap.put("COLESLAW", new String[]{"5500 ", "", ""});
            newPriceHashMap.put("ORIENTAL SALAD", new String[]{"7500 ", "", ""});
            newPriceHashMap.put("ROCCA SALAD", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("MOZZARELLA SALAD", new String[]{"11500 ", "", ""});
            newPriceHashMap.put("FRESH MOZZARELLA SALAD", new String[]{"19000 ", "", ""});
            newPriceHashMap.put("GREEK SALAD", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("TUNA SALAD", new String[]{"12000 ", "", ""});
            newPriceHashMap.put("CRAB SALAD", new String[]{"18000 ", "", ""});
            newPriceHashMap.put("CAESER SALAD", new String[]{"13000 ", "", ""});
            newPriceHashMap.put("CHEF'S SALAD", new String[]{"14000 ", "", ""});
            newPriceHashMap.put("SMOKED SALMON SALAD", new String[]{"24000 ", "", ""});
            newPriceHashMap.put("PASTA SALAD", new String[]{"12000 ", "", ""});
//DESSERTS
            newPriceHashMap.put("SALMON SET (18 pcs)", new String[]{"43000 ", "", ""});
            newPriceHashMap.put("TUNA SET (15 pcs)", new String[]{"42000 ", "", ""});
            newPriceHashMap.put("U MAI SET (16 pcs)", new String[]{"42000 ", "", ""});
            newPriceHashMap.put("SHIN SEN SET (17 pcs)", new String[]{"43000 ", "", ""});
            newPriceHashMap.put("HOLLYWOOD SPECIAL SET (15 pcs)", new String[]{"32000 ", "", ""});
            newPriceHashMap.put("CRISPY FUTO MAKI(3pcs)", new String[]{"9500 ", "", ""});
            newPriceHashMap.put("CRISPY FUTO MAKI(6pcs)", new String[]{"19000 ", "", ""});
            newPriceHashMap.put("SAKANA SET (29 pcs)", new String[]{"59000 ", "", ""});
            newPriceHashMap.put("HOLLYWOOD  ROYAL (35 pcs)", new String[]{"80000 ", "", ""});
            newPriceHashMap.put("UNA SET (23 pcs)", new String[]{"58000 ", "", ""});
            newPriceHashMap.put("A.T.SPECIAL VEGGIE SET (18 pcs)", new String[]{"35000 ", "", ""});
            newPriceHashMap.put("MIXED SET (9 pcs)", new String[]{"20000 ", "", ""});
            newPriceHashMap.put("VOLCANO SET", new String[]{"29000 ", "", ""});



        }



    }

    private void prepareListDataForDipnDip() {
        listDataHeaderForDipNdip = new ArrayList<>();
        listDataChildForDipNdip = new HashMap<>();

        // Adding child data Headers
        listDataHeaderForDipNdip.add("Crêpe");
        listDataHeaderForDipNdip.add("Waffle");
        listDataHeaderForDipNdip.add("Pancake");
        listDataHeaderForDipNdip.add("Chocolate rich");
        listDataHeaderForDipNdip.add("Baked goods");
        listDataHeaderForDipNdip.add("Ice cream");
        listDataHeaderForDipNdip.add("Chocolate shot");
        listDataHeaderForDipNdip.add("Fried yummies");
        listDataHeaderForDipNdip.add("Stuff in a cup");
        listDataHeaderForDipNdip.add("Dip Sticks");
        listDataHeaderForDipNdip.add("Dip n dip mania");
        listDataHeaderForDipNdip.add("COLD DRINKS");
        listDataHeaderForDipNdip.add("Milk shakes");
        listDataHeaderForDipNdip.add("Frappe");
        listDataHeaderForDipNdip.add("Smoothies");
        listDataHeaderForDipNdip.add("Ice tea shakes");
        listDataHeaderForDipNdip.add("Soft  drinks");

        List<String> crepe = new ArrayList<>();
        crepe.add("Dip n dip crêpe");
        crepe.add("Dip n dip crêpe with scoop");
        crepe.add("Fettuccini  crêpe full");
        crepe.add("Fettuccini  crêpe small");
        crepe.add("Cinnamon crêpe pouch");
        crepe.add("Tripple chocolate crêpe");
        crepe.add("Cookies crêpe");
        crepe.add("Mallow crêpe");
        crepe.add("Brownies crêpe");
        crepe.add("Krispy crêpe");
        crepe.add("Banana wrap");

        /// Waffle
        List<String> waffle = new ArrayList<>();
        waffle.add("Dip n dip waffle");
        waffle.add("Dip n dip waffle with ice cream");
        waffle.add("Chocolate waffle");
        waffle.add("Waffle stick");

        // pancake
        List<String> pancake = new ArrayList<>();
        pancake.add("Pancake mini tower");

        // Chocolate rich
        List<String> chocolateRich = new ArrayList<>();
        chocolateRich.add("Fondant");
        chocolateRich.add("Molten cake");
        chocolateRich.add("Brownies");
        chocolateRich.add("Dip n dip pizza");
        chocolateRich.add("Chocolate mousse");
        chocolateRich.add("Trois chocolat mousse");
        chocolateRich.add("Succès");

        /// Baked goods:
        List<String> bakedGoods = new ArrayList<>();

        bakedGoods.add("Mini muffin");
        bakedGoods.add("Cookies");
        bakedGoods.add("Dip n Dip éclair pyramid");
        bakedGoods.add("Éclair Kebab");
        bakedGoods.add("Profiterole");

        /// Ice cream:
        List<String> iceCream = new ArrayList<>();
        iceCream.add("Scoop");
        iceCream.add("scoop Tapped with chocolate");
        iceCream.add("Crunchy ice cream");

        /// Chocolate shot

        List<String> chocolateShot = new ArrayList<>();
        chocolateShot.add("Chocolate Shot");
        chocolateShot.add("Chocolate 1kg");

        //Fried yummies
        List<String> friedYammies = new ArrayList<>();
        friedYammies.add("Pain Perdu");
        friedYammies.add("Cheese cake nuggets");

        /// Stuff in a cup
        List<String> stuffInAcup = new ArrayList<>();
        stuffInAcup.add("Dip Crispies");
        stuffInAcup.add("Fruits In a cup");
        stuffInAcup.add("Brownies in a cup");

        ////  DIp sticks
        List<String> dipSticks = new ArrayList<>();
        dipSticks.add("Dip Sticks 4 pieces");
        dipSticks.add("Dip Sticks 8 pieces");
        dipSticks.add("Dip stick platter");

        // Dip n Dip Mania
        List<String> dipNdipMania = new ArrayList<>();
        dipNdipMania.add("Dip-able items (full plate)");
        dipNdipMania.add("Brownies, mini éclair");
        dipNdipMania.add("Ice cream (scoop)");
        dipNdipMania.add("Fondue");
        dipNdipMania.add("Whipped cream");

        /// cold drinks
        List<String> coldDrinks = new ArrayList<>();
        coldDrinks.add("Dip n dip freezy");

        /// MilkShakes
        List<String> milkShakes = new ArrayList<>();
        milkShakes.add("Chocolate milk shake");
        milkShakes.add("Vanilla milk shake");
        milkShakes.add("Strawberry  milk shake");
        milkShakes.add("Coffee shake");
        milkShakes.add("Banana milk shake");
        milkShakes.add("After eight milk shake");

        //// Frappe
        List<String> frappe = new ArrayList<>();
        frappe.add("Mocha frappe");
        frappe.add("White mocha frappe");
        frappe.add("Caramel frappe");
        frappe.add("Cookies frappe");
        frappe.add("Pink frappe");

        /// Smoothies
        List<String> smoothies = new ArrayList<>();
        smoothies.add("Creamy smoothies");
        smoothies.add("Smoothie float");
        smoothies.add("Oreo milk shake");
        smoothies.add("Affogato");

        /// Ice tea shakes
        List<String> iceTeaShakes = new ArrayList<>();
        iceTeaShakes.add("Ice tea shake");
        iceTeaShakes.add("Lemon tea shake");
        iceTeaShakes.add("Mint tea shake");
        iceTeaShakes.add("Peach tea shake");
        iceTeaShakes.add("Raspberry  tea shake");
        iceTeaShakes.add("Iced cappuccino");

        // Soft  drinks
        List<String> softDrinks = new ArrayList<>();
        softDrinks.add("Ice tea");
        softDrinks.add("Italian soda");
        softDrinks.add("Flavored cola");
        softDrinks.add("Pop");
        softDrinks.add("Perrier");
        softDrinks.add("Redbull");
        softDrinks.add("Spring water");


        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(0), crepe);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(1), waffle);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(2), pancake);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(3), chocolateRich);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(4), bakedGoods);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(5), iceCream);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(6), chocolateShot);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(7), friedYammies);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(8), stuffInAcup);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(9), dipSticks);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(10),dipNdipMania );
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(11), coldDrinks );
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(12), milkShakes );
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(13), frappe);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(14), smoothies);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(15), iceTeaShakes);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(16), softDrinks);
    }

    private void prePareDataListForPizzari() {
        listDataHeaderForPizzari = new ArrayList<>();
        listDataChildForPizzari = new HashMap<>();

        listDataHeaderForPizzari.add("VEGETARIAN PIZZAS");
        listDataHeaderForPizzari.add("Meat pizzas");
        listDataHeaderForPizzari.add("Sea food pizza");
        listDataHeaderForPizzari.add("PITA SANDWICHES");
        listDataHeaderForPizzari.add("Burger");
        listDataHeaderForPizzari.add("Salad");
        listDataHeaderForPizzari.add("Plates");
        listDataHeaderForPizzari.add("Beverages");

        List<String> Beverages = new ArrayList<>();
        Beverages.add("PEPSI");
        Beverages.add("7UP");
        Beverages.add("MIRINDA");
        Beverages.add("WATER SMALL");
        Beverages.add("WATER BIG");

        List<String> Plates = new ArrayList<>();
        Plates.add("Fish & Chips");
        Plates.add("French Fries");
        Plates.add("Extra Dressing");


        List<String> vegetratianPizza = new ArrayList<>();
        vegetratianPizza.add("Margherita");
        vegetratianPizza.add("Funghi");
        vegetratianPizza.add("Four cheese");
        vegetratianPizza.add("Rocca pizza");
        vegetratianPizza.add("Basil & cherry");
        vegetratianPizza.add("Vegetarian");
        vegetratianPizza.add("Vegetarian delight");
        vegetratianPizza.add("Broccoli pizza");

        List<String> meatPizza = new ArrayList<>();
        meatPizza.add("Vesuvio");
        meatPizza.add("Hawaiian");
        meatPizza.add("Capricciosa");
        meatPizza.add("Lebanese pizza");
        meatPizza.add("Pepperoni (beef)");
        meatPizza.add("Pepperoni (pork)");
        meatPizza.add("Salami");
        meatPizza.add("Du chef");
        meatPizza.add("Primavera");
        meatPizza.add("Carlo");
        meatPizza.add("BBQ chicken");
        meatPizza.add("Campagnolia");
        meatPizza.add("Calzone");
        meatPizza.add("Mexicana chicken");
        meatPizza.add("Eldorado beef");
        meatPizza.add("Lilla italy supreme");
        meatPizza.add("Soujouk pizza");
        meatPizza.add("Pastrami pizza");

        List<String> seaFood = new ArrayList<>();
        seaFood.add("Pescatore");
        seaFood.add("Paradiso");
        seaFood.add("Marinara");
        seaFood.add("Super tefat");
        seaFood.add("Seafood");
        seaFood.add("Quattro stagion");
        seaFood.add("Anchovy");
        seaFood.add("Super seafood");

        List<String> sandWitches = new ArrayList<>();
        sandWitches.add("Beef");
        sandWitches.add("Chicken");
        sandWitches.add("Tuna");
        sandWitches.add("Fish");

        List<String> burger = new ArrayList<>();
        burger.add("Beef burger");
        burger.add("Cheese burger");
        burger.add("Chicken burger");
        burger.add("Bacon & egg burger");
        burger.add("Fish burger");
        burger.add("Fish & chips plate");
        burger.add("French fries");
        burger.add("Extra meat");
        burger.add("Extra chicken");
        burger.add("Extra dressing");


        List<String> salad = new ArrayList<>();
        salad.add("Panachee");
        salad.add("Tuna");
        salad.add("Chicken");
        salad.add("Greek");
        salad.add("Shrimp");
        salad.add("Crab");
        salad.add("Ham & cheese");

        listDataChildForPizzari.put(listDataHeaderForPizzari.get(0), vegetratianPizza);
        listDataChildForPizzari.put(listDataHeaderForPizzari.get(1), meatPizza);
        listDataChildForPizzari.put(listDataHeaderForPizzari.get(2), seaFood);
        listDataChildForPizzari.put(listDataHeaderForPizzari.get(3), sandWitches);
        listDataChildForPizzari.put(listDataHeaderForPizzari.get(4), burger);
        listDataChildForPizzari.put(listDataHeaderForPizzari.get(5), salad);
        listDataChildForPizzari.put(listDataHeaderForPizzari.get(6), Plates);
        listDataChildForPizzari.put(listDataHeaderForPizzari.get(7), Beverages);
    }

    private void prepareListDataForLePoulet() {

        listDataHeaderForLePouelt = new ArrayList<>();
        listDataChildForLePouelt = new HashMap<>();

        listDataHeaderForLePouelt.add("SALADS");
        listDataHeaderForLePouelt.add("POTATOES");
        listDataHeaderForLePouelt.add("STARTERS");
        listDataHeaderForLePouelt.add("Sandwitch 3AL fahem");
        listDataHeaderForLePouelt.add("Farrouj Ghaz");
        listDataHeaderForLePouelt.add("Broasted Chicken");
        listDataHeaderForLePouelt.add("PLATTERS");
        listDataHeaderForLePouelt.add("DJEJ 3AL FAHEM");
        listDataHeaderForLePouelt.add("MACHEWE");
        listDataHeaderForLePouelt.add("DESSERT & BEVERAGES");

        ///SALADS
        List<String> salads = new ArrayList<>();
        salads.add("Season Salad");
        salads.add("Rocca Salad");
        salads.add("Chicken Salad");
        salads.add("Coleslaw Salad");
        salads.add("Tuna Salad");
        salads.add("Fattouch");
        salads.add("Fattouch (large)");

        // Potatoes
        List<String> potatoes = new ArrayList<>();
        potatoes.add("French Fries (plate)");
        potatoes.add("French Fries (large)");
        potatoes.add("French Fries (box)");
        potatoes.add("Broasted Potatoes");

        // STARTERS
        List<String> starters = new ArrayList<>();
        starters.add("Hommos");
        starters.add("Moutabal");

        // SANDWICH   3AL  FAHEM
        List<String> sandwichAlFahem = new ArrayList<>();
        sandwichAlFahem.add("Shish Tawouk");
        sandwichAlFahem.add("Shish Tawouk Platter");
        sandwichAlFahem.add("Lahme (lamb meat)");
        sandwichAlFahem.add("Kabab");
        sandwichAlFahem.add("Chicken");
        sandwichAlFahem.add("Steak");
        sandwichAlFahem.add("Fish Filet");
        sandwichAlFahem.add("Hamburger");
        sandwichAlFahem.add("Cheese Burger");
        sandwichAlFahem.add("Chicken Burger");
        sandwichAlFahem.add("Falafel");
        sandwichAlFahem.add("Makanek");
        sandwichAlFahem.add("Soujouk");
        sandwichAlFahem.add("Shawarma Lahme");
        sandwichAlFahem.add("Shawarma djej");
        sandwichAlFahem.add("Shawarma Lahme (large)");
        sandwichAlFahem.add("Shawarma Djej (large)");
        sandwichAlFahem.add("Shawarma Lahme (Saj bread)");
        sandwichAlFahem.add("Shawarma Djej (Saj bread)");
        sandwichAlFahem.add("Chicken Liver");
        sandwichAlFahem.add("Fajita");
        sandwichAlFahem.add("Crispy");
        sandwichAlFahem.add("Rosto");

        // FARROUJ  GHAZ
        List<String> farroujGhaz = new ArrayList<>();
        farroujGhaz.add("Grilled  Chicken");
        farroujGhaz.add("half Grilled Chicken");
        farroujGhaz.add("Grilled Chicken Special");

        ///Broasted  Chicken
        List<String> broastedChiken = new ArrayList<>();
        broastedChiken.add("Chicken");
        broastedChiken.add("Drumsticks (10pcs)");
        broastedChiken.add("Legs (8pcs)");
        broastedChiken.add("Wings (12pcs)");
        broastedChiken.add("Wings With Coriander (24pcs)");

        // PLATTERS
        List<String> platters = new ArrayList<>();
        platters.add("Chicken Filet (light)");
        platters.add("Meat Filet (light)");
        platters.add("Fish Filet (light)");
        platters.add("Shawarma Djej");
        platters.add("Shawarma Lahme");

        ///// DJEJ  3AL FAHEM
        List<String> djejFahem = new ArrayList<>();
        djejFahem.add("Chicken");
        djejFahem.add("Legs (8pcs)");
        djejFahem.add("Wings (12 pcs)");
        djejFahem.add("Wings With Coriander (12pcs)");

        /// MACHEWE
        List<String> machewe = new ArrayList<>();
        machewe.add("1 Kg Lahme Che2af");
        machewe.add("1 Kg Tawouk");
        machewe.add("1 Kg Kabab");
        machewe.add("1 Kg Machewe Mchakal");

        ///DESSERT  &  BEVERAGES
        List<String> dessertBeverages = new ArrayList<>();
        dessertBeverages.add("Jello");
        dessertBeverages.add("Custard");
        dessertBeverages.add("Laban 3iran");
        dessertBeverages.add("Water (small)");
        dessertBeverages.add("Water (big)");
        dessertBeverages.add("Pepsi");
        dessertBeverages.add("7-Up");
        dessertBeverages.add("Mirinda");
        dessertBeverages.add("Pepsi (1.25 L)");
        dessertBeverages.add("7-Up (1.25 L)");
        dessertBeverages.add("Mirinda (1.25 L)");
        dessertBeverages.add("Pepsi (2L)");
        dessertBeverages.add("7-Up (2L)");
        dessertBeverages.add("Mirinda (2L)");

        listDataChildForLePouelt.put(listDataHeaderForLePouelt.get(0), salads);
        listDataChildForLePouelt.put(listDataHeaderForLePouelt.get(1), potatoes);
        listDataChildForLePouelt.put(listDataHeaderForLePouelt.get(2), starters);
        listDataChildForLePouelt.put(listDataHeaderForLePouelt.get(3), sandwichAlFahem);
        listDataChildForLePouelt.put(listDataHeaderForLePouelt.get(4), farroujGhaz);
        listDataChildForLePouelt.put(listDataHeaderForLePouelt.get(5), broastedChiken);
        listDataChildForLePouelt.put(listDataHeaderForLePouelt.get(6), platters);
        listDataChildForLePouelt.put(listDataHeaderForLePouelt.get(7), djejFahem);
        listDataChildForLePouelt.put(listDataHeaderForLePouelt.get(8), machewe);
        listDataChildForLePouelt.put(listDataHeaderForLePouelt.get(9), dessertBeverages);
    }

    private void prepareListDataForBurgerHouse() {

        listDataHeaderForBurgerHouse = new ArrayList<>();
        listDataChildForBuregerHouse = new HashMap<>();

        listDataHeaderForBurgerHouse.add("The Authentic BURGER beef");
        listDataHeaderForBurgerHouse.add("APPETIZERS");
        listDataHeaderForBurgerHouse.add("SALADS");
        listDataHeaderForBurgerHouse.add("FISH & Chicken Burgers");
        listDataHeaderForBurgerHouse.add("Broasted Quality");
        listDataHeaderForBurgerHouse.add("Z Platters");
        listDataHeaderForBurgerHouse.add("Sub Sandwiches");


        List<String> platters = new ArrayList<>();
        platters.add("Z  3esmanliyé  Chicken");
        platters.add("White  House  Chicken");
        platters.add("Chicken Escalope");
        platters.add("Philadelphia  Platter");
        platters.add("Fajita  Platter");
        platters.add("Salmon  Platter");

        List<String> Broasted = new ArrayList<>();
        Broasted.add("Whole Broasted chicken");
        Broasted.add("Half Broasted chicken");
        Broasted.add("Combo: Broasted Chicken");
        Broasted.add("Combo: crispy tenders");
        Broasted.add("Broasted Potatoes");


        List<String> subz = new ArrayList<>();
        subz.add("Francisco Sandwich");
        subz.add("Francisco  Combo");

        subz.add("Fajita Sandwich");
        subz.add("Fajita Combo");

        subz.add("Chicken Sub  Sandwich");
        subz.add("Chicken Sub Combo");

        subz.add("Mexican Sandwich");
        subz.add("Mexican Combo");

        subz.add("Chicken Swiss  Sandwich");

        subz.add("Chicken Escalope  Sandwich");

        subz.add("Cuban Sandwich");

        subz.add("Chicken Escalope Combo");


        //subz.add("Cuban Combo");

        subz.add("Beef Avocado Sandwich");
        subz.add("Beef Avocado Combo");

        subz.add("Philly Steak Sandwich");
        subz.add("Philly Steak Combo");

        subz.add("Crab Sub Sandwich");
        subz.add("Crab Sub Combo");

        subz.add("Tuna Sub Sandwich");
        subz.add("Tuna Sub Combo");

        subz.add("Roast beef Sub Sandwich");
        subz.add("Roast beef Sub Combo");





        List<String> authenticBurgers = new ArrayList<>();
        authenticBurgers.add("Classic Sandwich");
        authenticBurgers.add("Classic Combo");
        authenticBurgers.add("Cheese burger Sandwich");
        authenticBurgers.add("Cheese burger Combo");
        authenticBurgers.add("Biggy house burger Sandwich");
        authenticBurgers.add("Biggy house burger Combo");
        authenticBurgers.add("BBq pepperoni Sandwich");
        authenticBurgers.add("BBq pepperoni Combo");
        authenticBurgers.add("Z Lebanese Burger Sandwich");
        authenticBurgers.add("Z Lebanese Burger Combo");
        authenticBurgers.add("Steak burger Sandwich");
        authenticBurgers.add("Steak burger Combo");
        authenticBurgers.add("Deluxe burger Sandwich");
        authenticBurgers.add("Deluxe burger Combo");
        authenticBurgers.add("Chili cheese burger Sandwich");
        authenticBurgers.add("Chili cheese burger Combo");
        authenticBurgers.add("Bacon house Sandwich");
        authenticBurgers.add("Bacon house Combo");
        authenticBurgers.add("Mozzarella beef burger Sandwich");
        authenticBurgers.add("Mozzarella beef burger Combo");
        authenticBurgers.add("Swiss mushroom Sandwich");
        authenticBurgers.add("Swiss mushroom Combo");
        authenticBurgers.add("Sushi burger Sandwich");
        authenticBurgers.add("Sushi burger Combo");
        authenticBurgers.add("Z angus house Sandwich");
        authenticBurgers.add("Z angus house Combo");
        authenticBurgers.add("Cheese at heart burger Sandwich");
        authenticBurgers.add("Cheese at heart burger Combo");
        authenticBurgers.add("Mexican house Sandwich");
        authenticBurgers.add("Mexican house Combo");
        authenticBurgers.add("Soujouk burger Sandwich");
        authenticBurgers.add("Soujouk burger Combo");
        authenticBurgers.add("Veggie burger Sandwich");
        authenticBurgers.add("Veggie burger Combo");
        authenticBurgers.add("Kids meal");
        authenticBurgers.add("Minions");

        /// APPETIZERS
        List<String> appitizers = new ArrayList<>();
        appitizers.add("Fries basket");
        appitizers.add("Cheese fries basket");
        appitizers.add("curly fries basket");
        appitizers.add("baked potatoes");
        appitizers.add("Potato wedges basket");
        appitizers.add("Mozzarella sticks");
        appitizers.add("Cheddar balls");
        appitizers.add("crispy crab or calamar");
        appitizers.add("chicken tenders");
        appitizers.add("chicken tenders grilled");
        appitizers.add("Chicken tenders dipped");
        appitizers.add("special crispy wings");
        appitizers.add("appetizers combo");

        //// SALADS:
        List<String> salads = new ArrayList<>();
        salads.add("Season salad");
        salads.add("Halloumi salad");
        salads.add("Greek salad");
        salads.add("Tuna Pasta");
        salads.add("Chef salad");
        salads.add("Steak Salad");
        salads.add("Crispy Chicken  Salad");
        salads.add("Chicken Caesar");
        salads.add("Crab Salad");
        salads.add("Rocca Salad");
        salads.add("Burger House Salad");

        /// BURGER  (FRESH CHICKEN)
        List<String> freshChiksBurgers = new ArrayList<>();
        freshChiksBurgers.add("Chicken Breast Burger Sandwich");
        freshChiksBurgers.add("Chicken Breast Burger Combo");
        freshChiksBurgers.add("Med Burger Sandwich");
        freshChiksBurgers.add("Med Burger Combo");
        freshChiksBurgers.add("Crispy Chicken Burger Sandwich");
        freshChiksBurgers.add("Crispy Chicken Burger Combo");
        freshChiksBurgers.add("Crunchy Chicken Fillet Sandwich");
        freshChiksBurgers.add("Crunchy Chicken Fillet Combo");
        freshChiksBurgers.add("Crunchy Fishy Fish Sandwich");
        freshChiksBurgers.add("Crunchy Fishy Fish Combo");
        freshChiksBurgers.add("Fried Fishy Burger Sandwich");
        freshChiksBurgers.add("Fried Fishy Burger Combo");
        freshChiksBurgers.add("Grilled Fishy Burger Sandwich");
        freshChiksBurgers.add("Grilled Fishy Burger Combo");

        listDataChildForBuregerHouse.put(listDataHeaderForBurgerHouse.get(0), authenticBurgers); // Header, Child data
        listDataChildForBuregerHouse.put(listDataHeaderForBurgerHouse.get(1), appitizers);
        listDataChildForBuregerHouse.put(listDataHeaderForBurgerHouse.get(2), salads);
        listDataChildForBuregerHouse.put(listDataHeaderForBurgerHouse.get(3), freshChiksBurgers);
        listDataChildForBuregerHouse.put(listDataHeaderForBurgerHouse.get(4), Broasted);
        listDataChildForBuregerHouse.put(listDataHeaderForBurgerHouse.get(5), platters);
        listDataChildForBuregerHouse.put(listDataHeaderForBurgerHouse.get(6), subz);


    }
    private void prepareListDataForShawarmaBar() {
        listDataHeaderForshawarmaBar = new ArrayList<>();
        listDataChildForshawarmaBar = new HashMap<>();

        // Adding child data Headers
        listDataHeaderForshawarmaBar.add("Shawarma");
        listDataHeaderForshawarmaBar.add("Our special combo");
        listDataHeaderForshawarmaBar.add("Soft Drinks");


        // Adding child data
        List<String> shawarmas = new ArrayList<>();
        shawarmas.add("Chicky Shawarma");
        shawarmas.add("Juicy Beef Shawarma");
        shawarmas.add("Beef Shawarma Bar");
        shawarmas.add("Shawarma Bites");
        shawarmas.add("Shawarma Soujouk");

        List<String> specialCombo = new ArrayList<>();
        specialCombo.add("2 shawarma djej");
        specialCombo.add("1 shawarma lahme 1 shawarma djej");
        specialCombo.add("2 shawarma lahme");
        specialCombo.add("Chicken");
        specialCombo.add("Beef");

        List<String> drinks = new ArrayList<>();
        drinks.add("Pepsi");
        drinks.add("Mirinda");
        drinks.add("Seven up");
        drinks.add("Water small");

        listDataChildForshawarmaBar.put(listDataHeaderForshawarmaBar.get(0), shawarmas); // Header, Child data
        listDataChildForshawarmaBar.put(listDataHeaderForshawarmaBar.get(1), specialCombo);
        listDataChildForshawarmaBar.put(listDataHeaderForshawarmaBar.get(2), drinks);

    }

    private void prepareListDataForSnackCharboul() {
        listDataHeaderForsnackCharboul = new ArrayList<>();
        listDataChildForsnackCharboul = new HashMap<>();

        // Adding child data Headers
        listDataHeaderForsnackCharboul.add("SANDWICHES");
        listDataHeaderForsnackCharboul.add("PLATES");
        listDataHeaderForsnackCharboul.add("COLD BEVERAGES");


        // Adding child data
        List<String> sandwiches = new ArrayList<>();
        sandwiches.add("Lahme");
        sandwiches.add("Tawouk");
        sandwiches.add("Kabab");
        sandwiches.add("Tawouk Special");
        sandwiches.add("Steak");
        sandwiches.add("Steak Special");
        sandwiches.add("Asabit Djej");
        sandwiches.add("Makanek");
        sandwiches.add("Soujouk");
        sandwiches.add("Hamburger");
        sandwiches.add("Cheese Burger");
        sandwiches.add("Chicken Burger");
        sandwiches.add("Fajita");
        sandwiches.add("Philadelphia");
        sandwiches.add("Rosto");
        sandwiches.add("Hot Dog");
        sandwiches.add("Hot Dog Special");
        sandwiches.add("Nkha3at");
        sandwiches.add("Sanesil");
        sandwiches.add("Batata");
        sandwiches.add("Kafta Naye");
        sandwiches.add("Habra Naye");
        sandwiches.add("Shawarma Lahme");
        sandwiches.add("Shawarma Djej");
        sandwiches.add("Lsenet");
        sandwiches.add("Djej");


        List<String> plates = new ArrayList<>();
        plates.add("Lahme Plate");
        plates.add("Tawouk Plate");
        plates.add("Kabab Plate");
        plates.add("Tawouk Special Plate");
        plates.add("Steak Plate");
        plates.add("Steak Special Plate");
        plates.add("Asabit Djej Plate");
        plates.add("Makanek Plate");
        plates.add("Soujouk Plate");
        plates.add("Hamburger Plate");
        plates.add("Cheese Burger Plate");
        plates.add("Chicken Burger Plate");
        plates.add("Fajita Plate");
        plates.add("Philadelphia Plate");
        plates.add("Rosto Plate");
        plates.add("Hot Dog Plate");
        plates.add("Hot Dog Special Plate");
        plates.add("Nkha3at Plate");
        plates.add("Sanesil Plate");
        plates.add("Batata Plate");
        plates.add("Kafte Naye Plate");
        plates.add("Habra Naye Plate");
        plates.add("Habra Naye Plate");
        plates.add("Shawarma Djej Plate");
        plates.add("Lsenet Plate");
        plates.add("Djej Plate");


        List<String> coldBeverages = new ArrayList<>();
        coldBeverages.add("Water Small");
        coldBeverages.add("Water Big");
        coldBeverages.add("Pepsi");
        coldBeverages.add("Mirinda");
        coldBeverages.add("7-Up");
        coldBeverages.add("Beer");

        // Header, Child data///
        listDataChildForsnackCharboul.put(listDataHeaderForsnackCharboul.get(0), sandwiches);
        listDataChildForsnackCharboul.put(listDataHeaderForsnackCharboul.get(1), plates);
        listDataChildForsnackCharboul.put(listDataHeaderForsnackCharboul.get(2), coldBeverages);
    }

    private void prepareDataForCroissant() {
        listDataHeaderForCroissants = new ArrayList<>();
        listDataChildForCroiassants = new HashMap<>();

        listDataHeaderForCroissants.add("Croissants");
        listDataHeaderForCroissants.add("CAKES / DOZEN");
        listDataHeaderForCroissants.add("CAKES / PIECE");
        listDataHeaderForCroissants.add("FRESH JUICE");
        listDataHeaderForCroissants.add("COLD BEVERAGES");
        listDataHeaderForCroissants.add("Frozen shake");
        listDataHeaderForCroissants.add("FRESH MOCKTAILS");
        listDataHeaderForCroissants.add("ICE CREAM");
        listDataHeaderForCroissants.add("ICED COFFEE DRINKS");
        listDataHeaderForCroissants.add("HOT BEVERAGES");

        List<String> crossants = new ArrayList<>();
        crossants.add("Chocolate");
        crossants.add("Knéfé");
        crossants.add("Zaatar");
        crossants.add("Zaatar Special");
        crossants.add("Olive & Mint");
        crossants.add("Olive & Mint Special");
        crossants.add("Spicy Cheese");
        crossants.add("Spicy Cheese Special");
        crossants.add("Cheese & Ham");
        crossants.add("Cheese & Ham Special");
        crossants.add("12 Small Croissant of any kind");


        List<String> cakes = new ArrayList<>();
        cakes.add("Tartez");
        cakes.add("Éclair");
        cakes.add("Chocolate Cream");


        List<String> cakeAndPiece = new ArrayList<>();
        cakeAndPiece.add("Baba au Rhum");
        cakeAndPiece.add("Baba au Rhum Special");
        cakeAndPiece.add("Éclair");
        cakeAndPiece.add("Éclair Special");
        cakeAndPiece.add("Tarte aux Fruits");
        cakeAndPiece.add("Sablé Chocolat");
        cakeAndPiece.add("Sablé Jam");
        cakeAndPiece.add("Mille Feuilles");
        cakeAndPiece.add("Boule Chocolat Coco");
        cakeAndPiece.add("Boule Chocolat Almond");
        cakeAndPiece.add("Cheese Cake");
        cakeAndPiece.add("Swiss Roll");

        List<String> freshJuice = new ArrayList<>();
        freshJuice.add("Orange");
        freshJuice.add("Grape Fruit");
        freshJuice.add("Carrot");
        freshJuice.add("Melon");
        freshJuice.add("Avocado");
        freshJuice.add("Apple");
        freshJuice.add("Strawberry");
        freshJuice.add("Lemonade");
        freshJuice.add("Banana With Milk");
        freshJuice.add("Kiwi, Strawberry & Lemon");
        freshJuice.add("Polo");
        freshJuice.add("Cocktail Pieces");
        freshJuice.add("Cocktail Juice");

        List<String> coldBeverages = new ArrayList<>();
        coldBeverages.add("Pepsi");
        coldBeverages.add("7-Up");
        coldBeverages.add("Mirinda");
        coldBeverages.add("Mexican Beer");
        coldBeverages.add("Beer");
        coldBeverages.add("AMP Energy");
        coldBeverages.add("H2OH");
        coldBeverages.add("Water (small)");
        coldBeverages.add("Water (big)");
        coldBeverages.add("Nuts");
        coldBeverages.add("Chips");

        List<String> freshMocktails = new ArrayList<>();
        freshMocktails.add("Strawberry Freez");
        freshMocktails.add("Mango Freez");
        freshMocktails.add("Mango Orange Freez");
        freshMocktails.add("Peach Freez");
        freshMocktails.add("Tropical Smoothie");
        freshMocktails.add("Survivor Smoothie");
        freshMocktails.add("Hulk");
        freshMocktails.add("Passion Fruit Reez");
        freshMocktails.add("Be Cool");
        freshMocktails.add("Italian Lemonade");
        freshMocktails.add("Blue Hawaiian Lemonade");
        freshMocktails.add("Pink Lemonade");
        freshMocktails.add("Frozen Bloody Orange");
        freshMocktails.add("Orange Mango Smoothie");
        freshMocktails.add("Kiwi");
        freshMocktails.add("Bubble Gum");
        freshMocktails.add("Virgin Colada");

        List<String> frozenShakes = new ArrayList<>();
        frozenShakes.add("Coconut Shake");
        frozenShakes.add("Melon Shake");
        frozenShakes.add("Strawberry Shake");
        frozenShakes.add("Vanilla Shake");
        frozenShakes.add("Minted Iced Chocolate");
        frozenShakes.add("Choco Oreo Shake");
        frozenShakes.add("Banana Berry Shake");
        frozenShakes.add("Chocolate Milk Shake");
        frozenShakes.add("Snickers Milk Shake");
        frozenShakes.add("Black & White");
        frozenShakes.add("Bubble Gum Shake");

        List<String> icecream = new ArrayList<>();
        icecream.add("Chocolat Mou");
        icecream.add("Chikita");
        icecream.add("Banana Split");

        List<String> icedCoffeeDrinks = new ArrayList<>();
        icedCoffeeDrinks.add("Coffee Frappe");
        icedCoffeeDrinks.add("Nescafe Frappe");
        icedCoffeeDrinks.add("Cappuccino Frappe");
        icedCoffeeDrinks.add("Caramel Frappucino");
        icedCoffeeDrinks.add("Toffee Banana Mocha");
        icedCoffeeDrinks.add("Frozen Tiramisu");
        icedCoffeeDrinks.add("After Eight Mocha");
        icedCoffeeDrinks.add("Long Island Frappe");
        icedCoffeeDrinks.add("Belgian Fever");
        icedCoffeeDrinks.add("Crunchy");

        List<String> hotBeverages =  new ArrayList<>();
        hotBeverages.add("Turkish Coffee");
        hotBeverages.add("Nescafe");
        hotBeverages.add("Doppio");
        hotBeverages.add("Cappuccino");
        hotBeverages.add("Flavored Latté");
        hotBeverages.add("Mochaccino");
        hotBeverages.add("Caramel Macchiato");
        hotBeverages.add("Tiramisu Latté");
        hotBeverages.add("Chocolate Cookies Latté");
        hotBeverages.add("Belgian Latté");
        hotBeverages.add("Crusty Mocha");
        hotBeverages.add("Flavored Hot Chocolate(hazelnut)");
        hotBeverages.add("Flavored hot chocolate(vanilla)");
        hotBeverages.add("Tea With Milk");

        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(0), crossants);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(1), cakes);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(2), cakeAndPiece);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(3), freshJuice);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(4), coldBeverages);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(5), freshMocktails);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(6), frozenShakes);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(7), icecream);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(8), icedCoffeeDrinks);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(9), hotBeverages);

    }

    private void prepareDateForDagher() {
        listDataHeaderForDagher = new ArrayList<>();
        listDataChildForDagher = new HashMap<>();

        listDataHeaderForDagher.add("Dagher Menu");

        List<String> dagher = new ArrayList<>();
        dagher.add("Samak");
        dagher.add("Chich Tawouk");
        dagher.add("Kabab");
        dagher.add("Lahme Che2af");
        dagher.add("2asabit Djej");
        dagher.add("Ma2ani2 Lahme");
        dagher.add("Crab");
        dagher.add("Sejou2");
        dagher.add("Marquise");
        dagher.add("Hot Dog");
        dagher.add("Hamburger");
        dagher.add("Cheese Burger");
        dagher.add("Chicken Burger");
        dagher.add("Potato");
        dagher.add("Machewe Mchakal (1 kilo)");
        dagher.add("Sa7en Machewe (small)");
        dagher.add("Sa7en Machewe (large)");
        dagher.add("Potato (big)");
        dagher.add("Shawarma Lahme");
        dagher.add("Shawarma Djej");
        dagher.add("Saniyit Shawarma Lahme (big)");
        dagher.add("Saniyit Shawarma Lahme (small)");
        dagher.add("Saniyit Shawarma Djej (big)");
        dagher.add("Saniyit Shawarma Djej (small)");
        dagher.add("Sandwich Falefil");
        dagher.add("Saniyit Falefil (big)");
        dagher.add("Saniyit Falefil (small)");
        dagher.add("Broasted");
        dagher.add("Broasted (sa7en)");
        dagher.add("Broasted Fish (1 kilo)");
        dagher.add("Broasted Fish (sa7en)");
        dagher.add("Pepsi");
        dagher.add("7-UP");
        dagher.add("Mirinda");
        dagher.add("Water (big)");
        dagher.add("Water (small)");
        dagher.add("Laban 3iran");

        listDataChildForDagher.put(listDataHeaderForDagher.get(0), dagher);

    }



    private void prepareListDataForAdonis() {
        listDataHeaderForAdonis = new ArrayList<>();
        listDataChildForAdonis = new HashMap<>();

        // Adding child data Headers
        listDataHeaderForAdonis.add("Sandwiches");
        listDataHeaderForAdonis.add("Fresh juices");
        listDataHeaderForAdonis.add("Fruit cocktails");


        // Adding child data
        List<String> sandwichies = new ArrayList<>();
        sandwichies.add("Roast beef");
        sandwichies.add("Ham & cheese");
        sandwichies.add("Tuna");
        sandwichies.add("Frankfurter");
        sandwichies.add("Tawouk");
        sandwichies.add("Chicken sub");
        sandwichies.add("Soujok");
        sandwichies.add("Makanek");
        sandwichies.add("Sub Marine");
        sandwichies.add("Jumbo burger");
        sandwichies.add("Phili steak");
        sandwichies.add("Kachkawen");
        sandwichies.add("+add cheese");
        sandwichies.add("+add ham&cheese");

        List<String> freshJuices = new ArrayList<>();
        freshJuices.add("Strawberry");
        freshJuices.add("Mixed cocktail");
        freshJuices.add("Pineapple");
        freshJuices.add("Mango");
        freshJuices.add("Guayava");
        freshJuices.add("Orange");
        freshJuices.add("Pomegrenade (winter)");
        freshJuices.add("Lemonade  (summer) ");
        freshJuices.add("Banana milk shake ");
        freshJuices.add("Strawbanana shake");
        freshJuices.add("Strawbanana milk shake");
        freshJuices.add("Melon");
        freshJuices.add("Apple");
        freshJuices.add("Carrot");
        freshJuices.add("Grapefruit");


        List<String> fruitCocktails = new ArrayList<>();
        fruitCocktails.add("Strawberry");
        fruitCocktails.add("Mango");
        fruitCocktails.add("Strawberry/mango");
        fruitCocktails.add("Avocado");
        fruitCocktails.add("Avocado/strawberry");
        fruitCocktails.add("Avocado/mango");
        fruitCocktails.add("TRIO");

        listDataChildForAdonis.put(listDataHeaderForAdonis.get(0), sandwichies); // Header, Child data
        listDataChildForAdonis.put(listDataHeaderForAdonis.get(1), freshJuices);
        listDataChildForAdonis.put(listDataHeaderForAdonis.get(2), fruitCocktails);
    }

    private void prepareListDataForLatour() {
        listDataHeaderForLatour = new ArrayList<>();
        listDataChildForLatour = new HashMap<>();

        // headings
        listDataHeaderForLatour.add("Pizza");
        listDataHeaderForLatour.add("Pasta Fiesta");
        listDataHeaderForLatour.add("Hot Sandwishes");
        listDataHeaderForLatour.add("Platters");
        listDataHeaderForLatour.add("Mana’ish");
        listDataHeaderForLatour.add("Drinks");

        // Pizza from Latour
        List<String> pizza = new ArrayList<>();
        pizza.add("Pizza Marguerita");
        pizza.add("Pizza 3 Fromages");
        pizza.add("Pizza Vegetarian");
        pizza.add("Pizza Jambon / Dinde");
        pizza.add("Pizza Pepperoni");
        pizza.add("Pizza Mexichicken");
        pizza.add("Pizza Jambon / Dinde");
        pizza.add("Pizza Pepperoni");
        pizza.add("Pizza Mexichicken");


        // Pasta Fiesta from Latour
        List<String> pastaFiesta = new ArrayList<>();
        pastaFiesta.add("Penne Arabiata");
        pastaFiesta.add("Chicken Pesto");
        pastaFiesta.add("Carbonara");

        // Hot Sandwichies from Latour
        List<String> hotSandwchies = new ArrayList<>();
        hotSandwchies.add("Tawook");
        hotSandwchies.add("American Frankfurter");
        hotSandwchies.add("Hamburger");
        hotSandwchies.add("Fish Burger");
        hotSandwchies.add("Fish Filet");
        hotSandwchies.add("Chicken Burger");
        hotSandwchies.add("Fajita");
        hotSandwchies.add("Philadelphia");
        hotSandwchies.add("Chicken La Tour");
        hotSandwchies.add("Beef Shawarma");
        hotSandwchies.add("Chicken Shawarma");
        hotSandwchies.add("Chicken Submarine");
        hotSandwchies.add("Chicken Club Sandwish");

        // Platters from Latour
        List<String> platters = new ArrayList<>();
        platters.add("Tawook");
        platters.add("Beef Shawarma");
        platters.add("Chicken Shawarma");
        platters.add("Fish & Chips");
        platters.add("Grilled Hamour Filet");
        platters.add("Chicken Breast");
        platters.add("Chicken Escalope");
        platters.add("Chicken Fungi");

        /// Mana’ish from Latour
        List<String> manaish = new ArrayList<>();

        manaish.add("Zaatar");
        manaish.add("Zaatar light");
        manaish.add("Zaatar and Labneh");
        manaish.add("Cheese");
        manaish.add("Spinach");
        manaish.add("Keshek");
        manaish.add("Akkawi");
        manaish.add("Halloum");
        manaish.add("Bulgari");
        manaish.add("Bulgari harra");
        manaish.add("Cheese Mix");
        manaish.add("Labneh");
        manaish.add("Lahme & Kawarma");
        manaish.add("Lahme b3ajin");
        manaish.add("Lahme b3ajin & Cheese");
        manaish.add("Pesto Cheese");
        manaish.add("Feta Mix");
        manaish.add("Turkey & Cheese");
        manaish.add("Ham & Cheese");
        manaish.add("Salami & Cheese");
        manaish.add("Hotdog & Cheese");
        manaish.add("La Tour Special");
        manaish.add("Soujouk & Cheese");
        manaish.add("Halloum Bacon");
        manaish.add("Chocoba");

        //// Drinks
        List<String> drinks = new ArrayList<>();
        drinks.add("pepsi");
        drinks.add("7up");
        drinks.add("Water");
        listDataChildForLatour.put(listDataHeaderForLatour.get(0), pizza);
        listDataChildForLatour.put(listDataHeaderForLatour.get(1), pastaFiesta);
        listDataChildForLatour.put(listDataHeaderForLatour.get(2), hotSandwchies);
        listDataChildForLatour.put(listDataHeaderForLatour.get(3), platters);
        listDataChildForLatour.put(listDataHeaderForLatour.get(4), manaish);
        listDataChildForLatour.put(listDataHeaderForLatour.get(5), drinks);
    }

    private void preparedListDataForRicaardo() {

        listDataHeaderForRicaardo = new ArrayList<>();
        listDataChildForRicaardo = new HashMap<>();

        // Headings
        listDataHeaderForRicaardo.add("Sandwiches");
        listDataHeaderForRicaardo.add("Soft Drinks");

        List<String> sandwiches = new ArrayList<>();
        sandwiches.add("Philadelphia");
        sandwiches.add("Spanish Steak");
        sandwiches.add("Francisco");
        sandwiches.add("Hamburger");
        sandwiches.add("Shrimp");
        sandwiches.add("Calamari");
        sandwiches.add("Roast Beef");
        sandwiches.add("Grilled chicken breast");
        sandwiches.add("Canarias");
        sandwiches.add("Mexican chicken");
        sandwiches.add("Ricky");
        sandwiches.add("Fajita");

        //// Drinks
        List<String> softDrinks = new ArrayList<>();
        softDrinks.add("pepsi");
        softDrinks.add("Mirinda");
        softDrinks.add("Seven up");
        softDrinks.add("Water");

        listDataChildForRicaardo.put(listDataHeaderForRicaardo.get(0), sandwiches);
        listDataChildForRicaardo.put(listDataHeaderForRicaardo.get(1), softDrinks);

    }



    private void preparedListDataForSubz() {

        listDataHeaderForSubz = new ArrayList<>();
        listDataChildForSubz = new HashMap<>();

        /// Headigs
        listDataHeaderForSubz.add("Salads");
        listDataHeaderForSubz.add("Burgers");
        listDataHeaderForSubz.add("SUBz SPECIAL");
        listDataHeaderForSubz.add("SUBZ FRIES");
        listDataHeaderForSubz.add("Drinks");




        // salads
        List<String> salads = new ArrayList<>();
        salads.add("Chicken Caesar");
        salads.add("Chef Salad");
        salads.add("Crab Salad");
        salads.add("Tuna Pasta Salad");
        salads.add("Subz Salad");


        // Burgers
        List<String> burgers = new ArrayList<>();
        burgers.add("Classic burger");
        burgers.add("Subz Burger");
        burgers.add("Chicken Burger");

        // SUBz SPECIAL
        List<String> subzSpecial = new ArrayList<>();
        subzSpecial.add("Fajitas");
        subzSpecial.add("Philadelphia");
        subzSpecial.add("Francisco");
        subzSpecial.add("Chicken Sub");
        subzSpecial.add("Mexican Chicken");
        subzSpecial.add("Chicken Escalope");
        subzSpecial.add("Torpedo");
        subzSpecial.add("Subz Steak");
        subzSpecial.add("Submarine");
        subzSpecial.add("Taouk");
        subzSpecial.add("Crispy");
        subzSpecial.add("Potato");
        subzSpecial.add("Crispy Plat");

        /// SUBZ FRIES
        List<String> subsFries = new ArrayList<>();
        subsFries.add("French Fries");
        subsFries.add("Wedges");
        subsFries.add("Twister");

        // Drinks
        List<String> drinks = new ArrayList<>();
        drinks.add("Pepsi");
        drinks.add("Seven Up");
        drinks.add("Mirinda");
        drinks.add("Water Small");
        drinks.add("Water Big");
        drinks.add("Beer Almaza");
        drinks.add("Ice Tea");

        listDataChildForSubz.put(listDataHeaderForSubz.get(0), salads);
        listDataChildForSubz.put(listDataHeaderForSubz.get(1), burgers);
        listDataChildForSubz.put(listDataHeaderForSubz.get(2), subzSpecial);
        listDataChildForSubz.put(listDataHeaderForSubz.get(3), subsFries);
        listDataChildForSubz.put(listDataHeaderForSubz.get(4), drinks);
    }



    private void preparedListDataForTanbakji() {

        listDataHeaderForTanbakji = new ArrayList<>();
        listDataChildForTanbakji = new HashMap<>();

        listDataHeaderForTanbakji.add("Salads");
        listDataHeaderForTanbakji.add("Mezza");
        listDataHeaderForTanbakji.add("RawMeat");
        listDataHeaderForTanbakji.add("Tanbakji Specials");
        listDataHeaderForTanbakji.add("Grill");
        listDataHeaderForTanbakji.add("Desserts");
        listDataHeaderForTanbakji.add("Beverages");
        listDataHeaderForTanbakji.add("Hot Beverages");
        listDataHeaderForTanbakji.add("Alcoholic Beverages");
        listDataHeaderForTanbakji.add("Wine");
        listDataHeaderForTanbakji.add("Narguile");


        List<String> Salads= new ArrayList<>();

        Salads.add("Season Salad");
        Salads.add("Fattouch");
        Salads.add("Tabboule");
        Salads.add("Raheb Salad");
        Salads.add("Zaatar, Rocca & Bakleh Salad");
        Salads.add("Halloum Salad");

        List<String> Mezza= new ArrayList<>();

        Mezza.add("Hommos ");
        Mezza.add("Hommos Beiruti");
        Mezza.add("Mutabal");
        Mezza.add("Mousakaa");
        Mezza.add("Labneh");
        Mezza.add("Goat Labneh");
        Mezza.add("Labneh with Garlic");
        Mezza.add("Tajen Fish");
        Mezza.add("Wark Enab");
        Mezza.add("Hendbeh Bel Zeit");
        Mezza.add("Basterma");
        Mezza.add("Batrakh");
        Mezza.add("Halloum Grilled");
        Mezza.add("Kebbeh Halabieh");
        Mezza.add("Rkakat Cheese & Sujok");
        Mezza.add("Rkakat Cheese");
        Mezza.add("Burak Cheese");
        Mezza.add("Sambousek Lahme");
        Mezza.add("Foul");
        Mezza.add("Balila");
        Mezza.add("Hommos Snawbar");
        Mezza.add("Hommos Awarma");
        Mezza.add("Hommos Makanek");
        Mezza.add("Hommos Sujok");
        Mezza.add("Hommos Meat & Snawbar");
        Mezza.add("Eggs Sumak");
        Mezza.add("Eggs Awarma");
        Mezza.add("Eggs Sujok");
        Mezza.add("Chicken Liver Tanbakji");
        Mezza.add("Makanek");
        Mezza.add("Sujok");
        Mezza.add("Frogs");
        Mezza.add("French Fries");
        Mezza.add("Potato Harra");
        Mezza.add("Potato Tanbakji");
        Mezza.add("Grilled Potatoes");
        Mezza.add("Grilled Wings");



        List<String> RawMeat= new ArrayList<>();
        RawMeat.add("Mixed Raw Meat");
        RawMeat.add("Raw Kebbeh with Chili");
        RawMeat.add("Raw Kebbeh");
        RawMeat.add("Raw Habra");
        RawMeat.add("Raw Kafta");



        List<String> TanbakjiSpecials= new ArrayList<>();

        TanbakjiSpecials.add("Falafel");
        TanbakjiSpecials.add("Sfiha Grilled");
        TanbakjiSpecials.add("Hommos Tanbakji");
        TanbakjiSpecials.add("Fatte Hommos");
        TanbakjiSpecials.add("Fatte Batenjan");
        TanbakjiSpecials.add("Chawarma Chicken");
        TanbakjiSpecials.add("Chawarma Meat");
        TanbakjiSpecials.add("Kebbeh Jabalieh");
        TanbakjiSpecials.add("Grilled Kebbeh with Labneh");
        TanbakjiSpecials.add("Kebab with Yogurt");



        List<String> Grill= new ArrayList<>();

        Grill.add("Grilled Meat (Lamb)");
        Grill.add("Grilled Meat (Beef)");
        Grill.add("Kebab Halabi");
        Grill.add("Kebab Batenjan");
        Grill.add("Kebab Orfali");
        Grill.add("Chich Taouk");
        Grill.add("Mashawi Mixed");
        Grill.add("Chicken Grilled Breast");
        Grill.add("Grilled Fish");
        Grill.add("Grilled Shrimps ");



        List<String> Desserts= new ArrayList<>();

        Desserts.add("Biscuit au Chocolat");
        Desserts.add("Mouhalabiye");
        Desserts.add("Crepes with Walnuts");
        Desserts.add("Layali Zahle");
        Desserts.add("Ice Cream ");

        List<String> Beverages= new ArrayList<>();

        Beverages.add("Mineral Water");
        Beverages.add("Mineral Water");
        Beverages.add("Perrier");
        Beverages.add("Soft Drinks");
        Beverages.add("Lemonade	");
        Beverages.add("Orange Juice");



        List<String> HotBeverages= new ArrayList<>();

        HotBeverages.add("Nescaf?");
        HotBeverages.add("Turkish Coffee");
        HotBeverages.add("White Coffee");
        HotBeverages.add("Tisane");



        List<String> AlcoholicBeverages= new ArrayList<>();

        AlcoholicBeverages.add("Almaza");
        AlcoholicBeverages.add("Whisky Extra , btl");
        AlcoholicBeverages.add("Whisky Extra ,");
        AlcoholicBeverages.add("Whisky Extra , gls");
        AlcoholicBeverages.add("Whisky Regular , btl ");
        AlcoholicBeverages.add("Whisky Regular ,");
        AlcoholicBeverages.add("Whisky Regular , gls");
        AlcoholicBeverages.add("Stolichnaya Vodka , btl");
        AlcoholicBeverages.add("Stolichnaya Vodka , gls");
        AlcoholicBeverages.add("Absolute Vodka , btl");
        AlcoholicBeverages.add("Absolute Vodka , gls");
        AlcoholicBeverages.add("Arak (Brun , Samir , Ksara) , btl");
        AlcoholicBeverages.add("Arak (Brun , Samir , Ksara) ,");
        AlcoholicBeverages.add("Arak (Brun , Samir , Ksara) ,");
        AlcoholicBeverages.add("Arak (Brun , Samir , Ksara) , gls");
        AlcoholicBeverages.add("Arak Al karram , btl ");
        AlcoholicBeverages.add("Arak Al karram ,");
        AlcoholicBeverages.add("Arak Al karram ,");
        AlcoholicBeverages.add("Arak Al karram , gls");



        List<String> Wine= new ArrayList<>();

        Wine.add("Chateau Ksara , btl ");
        Wine.add("Ksara Reserve du couvent , btl ");
        Wine.add("Ksara Reserve du couvent ,");
        Wine.add("Ksara Blanc de Blanc , btl");
        Wine.add("Ksara Blanc de Blanc ,");
        Wine.add("Ksara Sunset , btl ");
        Wine.add("Ksara Sunset ,");
        Wine.add("Domaine des Tourelles , btl");
        Wine.add("Nakad , btl");
        Wine.add("Warde Chateau les cedres , btl ");


        List<String> Narguile= new ArrayList<>();

        Narguile.add("Mouassal");
        Narguile.add("Ajami");





        listDataChildForTanbakji.put(listDataHeaderForTanbakji.get(0), Salads);
        listDataChildForTanbakji.put(listDataHeaderForTanbakji.get(1), Mezza);
        listDataChildForTanbakji.put(listDataHeaderForTanbakji.get(2), RawMeat);
        listDataChildForTanbakji.put(listDataHeaderForTanbakji.get(3), TanbakjiSpecials);
        listDataChildForTanbakji.put(listDataHeaderForTanbakji.get(4), Grill);
        listDataChildForTanbakji.put(listDataHeaderForTanbakji.get(5), Desserts);
        listDataChildForTanbakji.put(listDataHeaderForTanbakji.get(6), Beverages);
        listDataChildForTanbakji.put(listDataHeaderForTanbakji.get(7), HotBeverages);
        listDataChildForTanbakji.put(listDataHeaderForTanbakji.get(8), AlcoholicBeverages);
        listDataChildForTanbakji.put(listDataHeaderForTanbakji.get(9), Wine);
        listDataChildForTanbakji.put(listDataHeaderForTanbakji.get(10), Narguile);


    }
    private void preparedListDataForAdokit() {
        listDataHeaderForAdokit = new ArrayList<>();
        listDataChildForAdokit = new HashMap<>();

        listDataHeaderForAdokit.add("Mouneh");
        listDataHeaderForAdokit.add("FriedMouneh");
        listDataHeaderForAdokit.add("HealthyRetailGroceries");

        List<String> Mouneh = new ArrayList<>();
        Mouneh.add("Kebbeh Krass 6 pcs");
        Mouneh.add("Kebbeh halabiyeh 12 pcs");
        Mouneh.add("Kebbeh Zghertawiye 6 pcs");
        Mouneh.add("Kebbeh Awarma and Labaneh 6 pcs");
        Mouneh.add("Kebbeh samak");
        Mouneh.add("Kebbeh djej");
        Mouneh.add("Kebbeh Are3(pumpkin) 12 pcs");


        /// FriedMouneh
        List<String> FriedMouneh = new ArrayList<>();
        FriedMouneh.add("Fried Kebbeh Krass 6 pcs");
        FriedMouneh.add("Fried Kebbeh halabiyeh 12 pcs");
        FriedMouneh.add("Fried Kebbeh Zghertawiye 6 pcs");
        FriedMouneh.add("Fried Kebbeh Awarma and Labaneh 6 pcs");
        FriedMouneh.add("Fried Kebbeh samak");
        FriedMouneh.add("Fried Kebbeh djej");
        FriedMouneh.add("Fried Kebbeh Are3(pumpkin) 12 pcs");

        // Healthy Retail Groceries
        List<String> HealthyRetailGroceries = new ArrayList<>();
        HealthyRetailGroceries.add("Sambousik");
        HealthyRetailGroceries.add("Rkakat");
        HealthyRetailGroceries.add("Makanek (sausages) 500G");
        HealthyRetailGroceries.add("Soujok 500G");
        HealthyRetailGroceries.add("Keshek 500G");
        HealthyRetailGroceries.add("Awarma 500G");
        HealthyRetailGroceries.add("Goat Labaneh 500g");
        HealthyRetailGroceries.add("Warak 3enab 500g");
        HealthyRetailGroceries.add("Foor cheese 8 pcs");
        HealthyRetailGroceries.add("Pizza 8 pcs");
        HealthyRetailGroceries.add("Chicek cheese 8 pcs");
        HealthyRetailGroceries.add("Cheddar Hotdog 8 pcs");
        HealthyRetailGroceries.add("Olie-Thym cheese 8 pcs");
        HealthyRetailGroceries.add("Banana cinammon (caramelized) 8 pcs");
        HealthyRetailGroceries.add("Straw-cranberry dark chocolate Combo(8 mixed pc)");


        listDataChildForAdokit.put(listDataHeaderForAdokit.get(0), Mouneh);
        listDataChildForAdokit.put(listDataHeaderForAdokit.get(1), FriedMouneh);
        listDataChildForAdokit.put(listDataHeaderForAdokit.get(2), HealthyRetailGroceries);


    }



    private void preparedListDataForChoueiry() {

        listDataHeaderForChoueiry = new ArrayList<>();
        listDataChildForChoueiry = new HashMap<>();

        listDataHeaderForChoueiry.add("Akel");
        listDataHeaderForChoueiry.add("Beverages");

        /// Akel
        List<String> Akel = new ArrayList<>();

        Akel.add("Sandwich Djej");
        Akel.add("Sandwich 2asabit djej");
        Akel.add("Fakhd djej");
        Akel.add("Noss Farrouj");
        Akel.add("Farrouj kemil");
        Akel.add("Toum Kbir");
        Akel.add("Toum Zghir");
        Akel.add("Hoummoss kbir");
        Akel.add("Hoummoss zghir");

        /// Beverages
        List<String> Beverages = new ArrayList<>();
        Beverages.add("Pepsi tanak");
        Beverages.add("Mirinda tanak");
        Beverages.add("7up tanak");
        Beverages.add("May zghir");
        Beverages.add("May kbir");

        listDataChildForChoueiry.put(listDataHeaderForChoueiry.get(0), Akel);
        listDataChildForChoueiry.put(listDataHeaderForChoueiry.get(1), Beverages);
    }


    //prepare list for falafelelbeyt
    private void preparedListDataForfalafelelbeyt() {

        listDataHeaderForfalafelelbeyt = new ArrayList<>();
        listDataChildForfalafelelbeyt = new HashMap<>();

        listDataHeaderForfalafelelbeyt.add("Falafel");
        listDataHeaderForfalafelelbeyt.add("Beverages");

        /// Akel
        List<String> Falafel = new ArrayList<>();

        Falafel.add("Sandwich Falefil");
        Falafel.add("Falefil bi Khebez mar2ou2");
        Falafel.add("Dazinet Falefil bala khodra");
        Falafel.add("Dazinet Falefil ma3 khodra");
        Falafel.add("3elbit tarator");


        /// Beverages
        List<String> Beverages = new ArrayList<>();
        Beverages.add("Pepsi");
        Beverages.add("Mirinda");
        Beverages.add("7up");


        listDataChildForfalafelelbeyt.put(listDataHeaderForfalafelelbeyt.get(0), Falafel);
        listDataChildForfalafelelbeyt.put(listDataHeaderForfalafelelbeyt.get(1), Beverages);
    }

    private void preparedListDataFormrgrill() {

        listDataHeaderFormrgrill = new ArrayList<>();
        listDataChildFormrgrill = new HashMap<>();



        listDataHeaderFormrgrill.add("Salads");
        listDataHeaderFormrgrill.add("Cold Mezza");
        listDataHeaderFormrgrill.add("Hot Mezza");
        listDataHeaderFormrgrill.add("Al Mashawi");
        listDataHeaderFormrgrill.add("Cocktails Juices");
        listDataHeaderFormrgrill.add("Ice Cream");
        listDataHeaderFormrgrill.add("Desserts");
        listDataHeaderFormrgrill.add("MilkShakes");
        listDataHeaderFormrgrill.add("Special Cocktails");
        listDataHeaderFormrgrill.add("Drinks");
        listDataHeaderFormrgrill.add("Sandwiches");









        List<String> Salads= new ArrayList<>();
        Salads.add("Tabouleh");
        Salads.add("Fattouch");
        Salads.add("Mawsam Salad");
        Salads.add("Zaatar  Bakli  Rocca");
        Salads.add("Chmandar");
        Salads.add("khodra kabiss zaytoun");
        Salads.add("Jat khodra Mouchakal");
        Salads.add("Avocado Salad");
        Salads.add("Al Raheb Salad");


        List<String> ColdMezza= new ArrayList<>();
        ColdMezza.add("Hommos");
        ColdMezza.add("Hommos Beiruty");
        ColdMezza.add("Hommos Mr Grill");
        ColdMezza.add("Mtabal");
        ColdMezza.add("Labaneh");
        ColdMezza.add("Labaneh Bil Toum");
        ColdMezza.add("              Labaneh maez");
        ColdMezza.add("Labaneh maez extra");
        ColdMezza.add("Shanklish");
        ColdMezza.add("Tajen Samak");
        ColdMezza.add("            Basterma Arayes");
        ColdMezza.add("Warak Enab");
        ColdMezza.add("Ardi Chawki");
        ColdMezza.add("Hendbeh");
        ColdMezza.add("Batrakh");
        ColdMezza.add("Batata Mr Grill");
        ColdMezza.add("Batata Mishwieh");
        ColdMezza.add("Mkassarat");
        ColdMezza.add("   Mkassarat Extra");
        ColdMezza.add("Hommos Snawbar");
        ColdMezza.add("Loubieh Bi zeit");
        ColdMezza.add("Msakaa Batenjane");

        List<String> HotMezza= new ArrayList<>();
        HotMezza.add("Halloum Fried Grilled");
        HotMezza.add("Batata Meklieh");
        HotMezza.add("Batata Harra");
        HotMezza.add(" Falafel");
        HotMezza.add("Hommos  Awarma");
        HotMezza.add("Hommos  Lahmeh  Snawbar");
        HotMezza.add("Balila");
        HotMezza.add("Foul");
        HotMezza.add("Beyd Awarma");
        HotMezza.add("Makanek");
        HotMezza.add("Soujouk");
        HotMezza.add("Beyd Ghanam");
        HotMezza.add("                    Sawda Djej");
        HotMezza.add("Dafadeh");
        HotMezza.add("Batenjane Mekleh");
        HotMezza.add("Kousa Mekleh");
        HotMezza.add("Karnabeet Mekleh");
        HotMezza.add("Sahn Makaleh Mchakal");


        List<String> Sandwiches= new ArrayList<>();
        Sandwiches.add("Kafta");
        Sandwiches.add("Kabab Halabi");
        Sandwiches.add("Kabab Istambouli");
        Sandwiches.add("Kabab Orfali");
        Sandwiches.add("kabab Khachkhach");
        Sandwiches.add("kabab Bazinjan");
        Sandwiches.add("Kebbeh on The Grill");
        Sandwiches.add("Lahm Meshwe Lamb");
        Sandwiches.add("Lahm Meshwe Veal");
        Sandwiches.add("Makanek");
        Sandwiches.add("Soujouk");
        Sandwiches.add("Batata");
        Sandwiches.add("Falafel");
        Sandwiches.add("Labaneh");

        List<String> AlMashawi= new ArrayList<>();
        AlMashawi.add("Kabab Mr Grill Hot");
        AlMashawi.add("Kafta");
        AlMashawi.add("Kabab Halabi");
        AlMashawi.add("kabab Orfali Regular or Hot");
        AlMashawi.add("kabab Bazinjan");
        AlMashawi.add("Kabab Khachkhach");
        AlMashawi.add("Kabab Istambouli");
        AlMashawi.add("Kebbeh on the Grill");
        AlMashawi.add("Lahm Meshwe Lamb");
        AlMashawi.add("Lahm Meshwe Veal");
        AlMashawi.add("Lamb Chops");
        AlMashawi.add("Makanek");
        AlMashawi.add("Soujouk");
        AlMashawi.add("Farrouj Msahhab");
        AlMashawi.add("Taouk");
        AlMashawi.add("Wings");
        AlMashawi.add("Arayes Kafta");
        AlMashawi.add("Mashawi Mchakal");
        AlMashawi.add("Mashawi Mchakal kilo");
        AlMashawi.add("Lahm Meshwe Lamb kilo");
        AlMashawi.add("     Lahm Meshwe Veal kilo");
        AlMashawi.add("Kabab kilo");
        AlMashawi.add("Taouk kilo");
        AlMashawi.add("Grilled Hammour Fillet");
        AlMashawi.add("Jumbo Prawns");
        AlMashawi.add("Jumbo Prawns KG");
        AlMashawi.add("Assafir dozen");

        List<String> CocktailsJuices= new ArrayList<>();
        CocktailsJuices.add("Cocktail ");
        CocktailsJuices.add("Cocktail juice ");
        CocktailsJuices.add("Avocado");
        CocktailsJuices.add("Kiwi ");
        CocktailsJuices.add("Mango");
        CocktailsJuices.add("Pineapple");
        CocktailsJuices.add("Strawberry");
        CocktailsJuices.add("Orange");
        CocktailsJuices.add("Lemonade");
        CocktailsJuices.add("Mint Lemonade");

        List<String> IceCream = new ArrayList<>();
        IceCream.add("Ice Cream cup");



        List<String> Desserts= new ArrayList<>();
        Desserts.add("Mouhalabieh");
        Desserts.add("Oriental Sweets");

        List<String> MilkShakes= new ArrayList<>();
        MilkShakes.add("Banana Milkshake");
        MilkShakes.add("Chocolate Banana Milkshake");
        MilkShakes.add("Strawberry Milkshake");

        List<String> Specialcocktail= new ArrayList<>();
        Specialcocktail.add("Turbo ");
        Specialcocktail.add("Olympic");
        Specialcocktail.add("Tahiti");
        Specialcocktail.add("Fruit salad");
        Specialcocktail.add("Exotic plates");


        List<String> Drinks= new ArrayList<>();
        Drinks.add("Red Bull");
        Drinks.add("Mineral Water Small");
        Drinks.add("Mineral Water Large");
        Drinks.add("Soft Drinks");
        Drinks.add("Perrier");
        Drinks.add("laziza");
        Drinks.add("Local Beer");
        Drinks.add("Almaza light");




        listDataChildFormrgrill.put(listDataHeaderFormrgrill.get(0), Salads);
        listDataChildFormrgrill.put(listDataHeaderFormrgrill.get(1), ColdMezza);
        listDataChildFormrgrill.put(listDataHeaderFormrgrill.get(2), HotMezza);
        listDataChildFormrgrill.put(listDataHeaderFormrgrill.get(4), AlMashawi);
        listDataChildFormrgrill.put(listDataHeaderFormrgrill.get(5), CocktailsJuices);
        listDataChildFormrgrill.put(listDataHeaderFormrgrill.get(6), IceCream);
        listDataChildFormrgrill.put(listDataHeaderFormrgrill.get(7), Desserts);
        listDataChildFormrgrill.put(listDataHeaderFormrgrill.get(7), MilkShakes);
        listDataChildFormrgrill.put(listDataHeaderFormrgrill.get(8), Specialcocktail);
        listDataChildFormrgrill.put(listDataHeaderFormrgrill.get(9), Drinks);
        listDataChildFormrgrill.put(listDataHeaderFormrgrill.get(10), Sandwiches);
    }
    private void preparedListDataForHollywood() {

        listDataHeaderForhollywood = new ArrayList<>();
        listDataChildForhollywood = new HashMap<>();

        listDataHeaderForhollywood.add("SPEICAL WRAP MAKI (4PCs)");
        listDataHeaderForhollywood.add("MAIN COURSES");
        listDataHeaderForhollywood.add("SALTED CREPES");
        listDataHeaderForhollywood.add("TEKAMI");
        listDataHeaderForhollywood.add("STARTERS & APPETIZERS");
        listDataHeaderForhollywood.add("TOBICO URA MAKI");
        listDataHeaderForhollywood.add("LEBANESE FOOD");
        listDataHeaderForhollywood.add("PASTA");
        listDataHeaderForhollywood.add("LIGHT MENU");
        listDataHeaderForhollywood.add("SUSHI");
        listDataHeaderForhollywood.add("SESAME URA MAKL(3PCS)");
        listDataHeaderForhollywood.add("SANDWICH PLATTERS");
        listDataHeaderForhollywood.add("CHINES FOOD");
        listDataHeaderForhollywood.add("HOLLYWOOD CAFE'S SPECIALITIES");
        listDataHeaderForhollywood.add("SOUP");
        listDataHeaderForhollywood.add("SPECIAL SETS");
        listDataHeaderForhollywood.add("HOSOMAKI");
        listDataHeaderForhollywood.add("SALAD CORNER");
        listDataHeaderForhollywood.add("SASHIMI 3 PCs");
        listDataHeaderForhollywood.add("CRISPY URA MAKI (3PCs)");
        listDataHeaderForhollywood.add("PIZZAS");
        listDataHeaderForhollywood.add("SUCHI SALAD");
        listDataHeaderForhollywood.add("RICE");
        listDataHeaderForhollywood.add("DESSERTS");


        List<String>SPEICALWRAPMAKI = new ArrayList<>();
        SPEICALWRAPMAKI.add("SPICY SALMON");
        SPEICALWRAPMAKI.add("SALMON");
        SPEICALWRAPMAKI.add("MANGO");
        SPEICALWRAPMAKI.add("CALIFORNIA");
        SPEICALWRAPMAKI.add("CANADIAN MAKI");

        List<String>MAINCOURSES= new ArrayList<>();
        MAINCOURSES.add("CREAM OF MUSHROOM");
        MAINCOURSES.add("SOUP OF THE DAY");

        List<String>SALTEDCREPES= new ArrayList<>();
        SALTEDCREPES.add("GRILLED �HAMOUR� FILLET");
        SALTEDCREPES.add("GRILLED SALMON FILLET");
        SALTEDCREPES.add("MIXED FRIED SEAFOOD");
        SALTEDCREPES.add("HOLLYWOOD CAF� GRILLED PRAWNS");
        SALTEDCREPES.add("CRISPY TEMPURA FRIED PRAWNS");
        SALTEDCREPES.add("SHRIMPS WITH CURRY SAUCE");
        SALTEDCREPES.add("CHIKEN MUSTARD SAUCE");
        SALTEDCREPES.add("CHICKEN RAGOUT");
        SALTEDCREPES.add("CHICKEN ESCALOPE");
        SALTEDCREPES.add("CHICKEN CORDN BLEU");
        SALTEDCREPES.add("ELEGANT CHICKEN");
        SALTEDCREPES.add("INDIAN CHIKEN CURRY");
        SALTEDCREPES.add("PEPPER CHICKEN STEAK");
        SALTEDCREPES.add("CHIKEN FAJITA");
        SALTEDCREPES.add("ESCALLOPE VIENNOISE");
        SALTEDCREPES.add("STEAK ROBERT");
        SALTEDCREPES.add("STEAK AU POIVRE");
        SALTEDCREPES.add("STEAK ROQUEFORT");
        SALTEDCREPES.add("CHATEAUBRIAND");
        SALTEDCREPES.add("GRILLED FILLET ROSSINI");
        SALTEDCREPES.add("BEEF STROGSNOFF");



        List<String>TEKAMI= new ArrayList<>();
        TEKAMI.add("CRISPY SALMON");
        TEKAMI.add("CRISPY TUNA");
        TEKAMI.add("SPICY URA SALMON");
        TEKAMI.add("KANI SALMON");
        TEKAMI.add("HANAMI SALMON");
        TEKAMI.add("CRISPY SCALLOPS");
        TEKAMI.add("CRISPY CALIFORNIA");
        TEKAMI.add("CRISPY EEL");
        TEKAMI.add("CRISPY SHRIMP");
        TEKAMI.add("CRISPY UNA");
        TEKAMI.add("TEMPURA ROLL");

        List<String>STARTERSAPPETIZERS= new ArrayList<>();
        STARTERSAPPETIZERS.add("TUNA");
        STARTERSAPPETIZERS.add("FISH BURGER");
        STARTERSAPPETIZERS.add("CHICH TAOUK");
        STARTERSAPPETIZERS.add("CHICKEN BURGER");
        STARTERSAPPETIZERS.add("HAMBURGER");
        STARTERSAPPETIZERS.add("SPICY BURGER");
        STARTERSAPPETIZERS.add("CHEESE BURGER");
        STARTERSAPPETIZERS.add("BACON CHEESE BURGER");



        List<String>TOBICOURAMAKI= new ArrayList<>();
        TOBICOURAMAKI.add("SALMON");
        TOBICOURAMAKI.add("TUNA");
        TOBICOURAMAKI.add("SCALLOPS");
        TOBICOURAMAKI.add("SHRIMP");
        TOBICOURAMAKI.add("CRAB");
        TOBICOURAMAKI.add("EEL");
        TOBICOURAMAKI.add("SPICY SALMON");
        TOBICOURAMAKI.add("CRISPY SALMON");

        List<String>LEBANESEFOOD= new ArrayList<>();
        LEBANESEFOOD.add("SPAGHETTI WITH TOMATO SAUCE & CHEESE");
        LEBANESEFOOD.add("SPAGHETTI WITH WHITE SAUCE & CHEESE");
        LEBANESEFOOD.add("CURTIS PRAWNS IN TAGLIATELLE");
        LEBANESEFOOD.add("SPICY SPAGHETTI WITH SHRIMPS");
        LEBANESEFOOD.add("TAGLIATELLE SALMON");



        List<String>PASTA= new ArrayList<>();
        PASTA.add("STEAMED RICE");
        PASTA.add("FRIED RICE WITH CORN");

        List<String>LIGHTMENU= new ArrayList<>();
        LIGHTMENU.add("SESAMI BUTTER FRIED CHICKEN");
        LIGHTMENU.add("BRAISED FILLET OF BEEF");
        LIGHTMENU.add("BEEF TERMIDOS CINZANO SAUCE ");
        LIGHTMENU.add("MINI STEAK IN BACON MUSHROOM & ONION");
        LIGHTMENU.add("AMERICAN T-BONE STEAK (450G)");
        LIGHTMENU.add("AMERICAN TENDERLOIN STEAK");
        LIGHTMENU.add("AUSTRALIAN TENDERLOIN");

        List<String>SUSHI= new ArrayList<>();
        SUSHI.add("SALAMON");
        SUSHI.add("TUNA");
        SUSHI.add("EEL");
        SUSHI.add("SCALLOPS");
        SUSHI.add("HAMACHI");
        SUSHI.add("WHITE FISH");
        SUSHI.add("SHRIMPS");
        SUSHI.add("CRAB");

        List<String>SESAMEURAMAKL= new ArrayList<>();
        SESAMEURAMAKL.add("SPECIAL CRAZY");
        SESAMEURAMAKL.add("SPECIAL CALIFORNIA");
        SESAMEURAMAKL.add("SHRIMP TOBICO");
        SESAMEURAMAKL.add("PHILI-SALMON");


        List<String>SANDWICHPLATTERS= new ArrayList<>();
        SANDWICHPLATTERS.add("SLICES BAKED POTATO");
        SANDWICHPLATTERS.add("SLICED BAKED POTATO WITH CHEESE");
        SANDWICHPLATTERS.add("FRENCH FRIES");
        SANDWICHPLATTERS.add("FRENCH FRIES WITH CHEDDAR CHEESE");
        SANDWICHPLATTERS.add("FRIED POTATO WEDGES");
        SANDWICHPLATTERS.add("TWISTER FRENCH FRIES");
        SANDWICHPLATTERS.add("TWISTER FRENCH FRIES WITH CHEDDAR CHEESE");
        SANDWICHPLATTERS.add("CROQUETTE POTATO");
        SANDWICHPLATTERS.add("GARLIC CHEESE BREAD (5PCS)");
        SANDWICHPLATTERS.add("FRIED MOZZARELLA STICKS WITH CHILLY SAUCE (6 PCS)");
        SANDWICHPLATTERS.add("CHEESE OMELETTE");
        SANDWICHPLATTERS.add("MIXED FRIED SEAFOOD");
        SANDWICHPLATTERS.add("SHRIMP COCKTAIL");
        SANDWICHPLATTERS.add("SMOKED SCOTTISH SALMON");
        SANDWICHPLATTERS.add("CHICKEN NUGGETS (5PCS)");
        SANDWICHPLATTERS.add("BRESAOLA");

        List<String>CHINESFOOD= new ArrayList<>();
        CHINESFOOD.add("CREATE YOUR OWN CREPE");

        List<String>HOLLYWOODCAFESPECIALITIES= new ArrayList<>();
        HOLLYWOODCAFESPECIALITIES.add("NAPOLITANA PIZZA");
        HOLLYWOODCAFESPECIALITIES.add("PROSCIUTTO PIZZA");
        HOLLYWOODCAFESPECIALITIES.add("TUNA PIZZA");
        HOLLYWOODCAFESPECIALITIES.add("EXOTIC PIZZA");
        HOLLYWOODCAFESPECIALITIES.add("SPICY HOT PIZZA");
        HOLLYWOODCAFESPECIALITIES.add("HOLLYWOOD CAF� PIZZA");


        List<String>SOUP= new ArrayList<>();
        SOUP.add("CROQUE MONSIEUR");
        SOUP.add("THE CLUB");
        SOUP.add("DOUBLE FISH BURGER");
        SOUP.add("DOUBLE CHICKEN BURRGER");
        SOUP.add("STEAK SANDWICH");
        SOUP.add("TORNADO");
        SOUP.add("DOUBLE HAMBURGER");
        SOUP.add("DOUBLE CHEESE BURGER");
        SOUP.add("DOUBLE BACON CHEESE BURGER");

        List<String> SPECIALSETS= new ArrayList<>();
        SPECIALSETS.add("CRISPY SALMON");
        SPECIALSETS.add("CRISPY TUNA");
        SPECIALSETS.add("CRISPY CRAB");
        SPECIALSETS.add("IKURA SALMON");
        SPECIALSETS.add("SAKE");



        List<String>HOSOMAKI= new ArrayList<>();
        HOSOMAKI.add("SALMON");
        HOSOMAKI.add("SHRIMP");
        HOSOMAKI.add("WHITE FISH");
        HOSOMAKI.add("SCALLOPS");
        HOSOMAKI.add("TUNA");
        HOSOMAKI.add("EEL");
        HOSOMAKI.add("CRAB");
        HOSOMAKI.add("SHRIMP TEMPURA");

        List<String>SALADCORNER= new ArrayList<>();
        SALADCORNER.add("VEGETABLE SPRING ROLLS");
        SALADCORNER.add("VEGETERIAN SPAGHETTI");
        SALADCORNER.add("FISH FILLET WITH GINGER SOYA SAUCE");
        SALADCORNER.add("FISH FILLET WITH BLACK BEANS SAUCE");
        SALADCORNER.add("SWEET AND SOUR SHRIMPS");
        SALADCORNER.add("CURRY SHRIMPS IN HOT POT");
        SALADCORNER.add("SHRIMPS WITH GINGER AND ONION");
        SALADCORNER.add("NEEHUOON NOODLES CHICKEN & SHRIMPS");
        SALADCORNER.add("CANTON FRIED CRISPY NOODLES CHICKEN & SHRIMPS");
        SALADCORNER.add("SWEET AND SOUR CHICKEN");
        SALADCORNER.add("CHILI CHICKEN WITH CASHEW NUTS");
        SALADCORNER.add("CHICKEN IN SATAY SAUCE");
        SALADCORNER.add("CHICKEN WITH MIXED VEGETABLES");
        SALADCORNER.add("CURRY CHICKEN IN HOT POT");
        SALADCORNER.add("CHICKEN IN GINGER HONEY SAUCE");
        SALADCORNER.add("CURRY BEEF IN HOT POT");
        SALADCORNER.add("BEEF IN OYSTER SAUCE");
        SALADCORNER.add("BEEF WITH GREEN PEPPER IN BLACK BEANS SAUCE");
        SALADCORNER.add("BEEF CASHEW IN HOT POT");
        SALADCORNER.add("SHANGHAI BEEF NOODLES");


        List<String>SASHIMI= new ArrayList<>();
        SASHIMI.add("EDAMAME");
        SASHIMI.add("CRISPY SPICY SALMON CRAB SALAD");
        SASHIMI.add("CRISPY SALMO N SALAD");
        SASHIMI.add("TUNA SALAD");
        SASHIMI.add("SALMON SALAD");
        SASHIMI.add("CALAMARI SALAD");

        List<String>CRISPYURAMAKI= new ArrayList<>();
        CRISPYURAMAKI.add("SPANISH ROLL");
        CRISPYURAMAKI.add("URA SHRIMP");
        CRISPYURAMAKI.add("FLYING SALMON");
        CRISPYURAMAKI.add("SPECIAL EEL");
        CRISPYURAMAKI.add("SPECIAL URA");
        CRISPYURAMAKI.add("DEEP MANGO");
        CRISPYURAMAKI.add("SKIN DEEP");
        CRISPYURAMAKI.add("KANI MANGO");
        CRISPYURAMAKI.add("CINEMA ROLL");
        CRISPYURAMAKI.add("OH MY GOD");

        List<String>PIZZAS= new ArrayList<>();
        PIZZAS.add("FATTOUCH");
        PIZZAS.add("CHICH TAOUK");


        List<String>SUCHISALAD= new ArrayList<>();
        SUCHISALAD.add("ROCCA N�ROLL CHICKEN");
        SUCHISALAD.add("QUINOA SALAD");
        SUCHISALAD.add("LIGHT TUNA PASTA SALAD");
        SUCHISALAD.add("TOFU SALAD");
        SUCHISALAD.add("LIGHT HAMOUR");
        SUCHISALAD.add("HOLLYWOOD SPECIAL DIET PIZZA");
        SUCHISALAD.add("FUSILLI ALA ROMANO");
        SUCHISALAD.add("LIGHT STEAK AU POIVRE");
        SUCHISALAD.add("D-LIGHT CHICKEN");
        SUCHISALAD.add("BREAST CHICKEN WITH RED WINE");
        SUCHISALAD.add("STEAK MARINADE");
        SUCHISALAD.add("SMOKED TURKEY");
        SUCHISALAD.add("EVER SLIM CHICKEN BURGER");
        SUCHISALAD.add("FIT N' BURGER");
        SUCHISALAD.add("CHICKEN TORTILLAS");



        List<String>RICE= new ArrayList<>();
        RICE.add("COLESLAW");
        RICE.add("ORIENTAL SALAD");
        RICE.add("ROCCA SALAD");
        RICE.add("MOZZARELLA SALAD");
        RICE.add("FRESH MOZZARELLA SALAD");
        RICE.add("GREEK SALAD");
        RICE.add("TUNA SALAD");
        RICE.add("CRAB SALAD");
        RICE.add("CAESER SALAD");
        RICE.add("CHEF'S SALAD");
        RICE.add("SMOKED SALMON SALAD");
        RICE.add("PASTA SALAD");


        List<String>DESSERTS= new ArrayList<>();
        DESSERTS.add("SALMON SET (18 pcs)");
        DESSERTS.add("TUNA SET (15 pcs)");
        DESSERTS.add("U MAI SET (16 pcs)");
        DESSERTS.add("SHIN SEN SET (17 pcs)");
        DESSERTS.add("HOLLYWOOD SPECIAL SET (15 pcs)");
        DESSERTS.add("CRISPY FUTO MAKI(3pcs)");
        DESSERTS.add("CRISPY FUTO MAKI(6pcs)");
        DESSERTS.add("SAKANA SET (29 pcs)");
        DESSERTS.add("HOLLYWOOD  ROYAL (35 pcs)");
        DESSERTS.add("UNA SET (23 pcs)");
        DESSERTS.add("A.T.SPECIAL VEGGIE SET (18 pcs)");
        DESSERTS.add("MIXED SET (9 pcs)");
        DESSERTS.add("VOLCANO SET");




        listDataChildForhollywood.put(listDataHeaderForhollywood.get(0),SPEICALWRAPMAKI);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(1),MAINCOURSES);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(2),SALTEDCREPES);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(3),TEKAMI);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(4),STARTERSAPPETIZERS);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(5),TOBICOURAMAKI);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(6),LEBANESEFOOD);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(7),PASTA);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(8),LIGHTMENU);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(9),SUSHI);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(10),SESAMEURAMAKL);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(11),SANDWICHPLATTERS);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(12),CHINESFOOD);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(13),HOLLYWOODCAFESPECIALITIES);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(14),SOUP);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(15),SPECIALSETS);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(16),HOSOMAKI);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(17),SALADCORNER);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(18),SASHIMI);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(19),CRISPYURAMAKI);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(20),PIZZAS);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(21),SUCHISALAD);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(22),RICE);
        listDataChildForhollywood.put(listDataHeaderForhollywood.get(23),DESSERTS);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_details_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent upIntent = new Intent(this, ProductsActivity.class);
        switch (item.getItemId()) {
            case R.id.action_checkOut:
                startActivity(new Intent(getApplicationContext(), CartActivity.class));
                break;
            case android.R.id.home:
                NavUtils.navigateUpTo(this, upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
