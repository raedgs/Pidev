/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionpromotion.Service;

import gestionpromotion.Entity.CodeCoupon;
import gestionpromotion.Entity.Promotion;



/**
 *
 * @author Mortadha
 */
public interface MyListener {
    
    public void onClickListener(CodeCoupon c);
    public void onClickListener2(Promotion p);
}
