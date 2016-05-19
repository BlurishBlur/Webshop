/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Alexander
 */
public interface IDatabase {

    void connectPIM();

    void connectURM();

    void disconnectPIM();

    void disconnectURM();

    boolean isPIMConnected();

    boolean isURMConnected();

    void pim();
    
}
