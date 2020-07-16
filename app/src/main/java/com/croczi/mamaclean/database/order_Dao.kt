package com.mustafayusef.sharay.database

import androidx.room.*
import com.mustafayusef.sharay.database.entitis.PackageOrder
import com.mustafayusef.sharay.database.entitis.catagoryOrder

import retrofit2.Response



@Dao
interface order_Dao {
    //Category
    @Query("select * from catagoryOrder")
    suspend fun getAllCategoryOrder():List<catagoryOrder>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertCategoryOrder(cars: catagoryOrder):Long

    @Query("DELETE FROM catagoryOrder")
    suspend fun deleteAllCategoryOrder()

      @Query("delete from catagoryOrder where id=:id")
    suspend fun deleteCatOrderById(id: Int?):Int


//Category

    //Package
    @Query("select * from PackageOrder")
    suspend fun getAllPackageOrder():List<PackageOrder>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPackageOrder(cars: PackageOrder):Long


    @Query("DELETE FROM PackageOrder")
    suspend fun deleteAllPackageOrder()

    @Query("delete from PackageOrder where Item_id=:id")
    suspend fun deletePackOredrById(id: Int?):Int
    //Package
//  @Query("delete from latestCar where id=:id")
//   fun deletsurvayById(id: Int?):Int



//    @Query("DELETE FROM latestCar where idDb NOT IN (SELECT idDb from latestCar ORDER BY idDb DESC LIMIT 10)")
//    suspend fun deleteCars():Int


}