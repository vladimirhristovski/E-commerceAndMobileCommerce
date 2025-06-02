import React from 'react';
import ProductsPage from "./ui/pages/ProductsPage/ProductsPage.jsx";
import {BrowserRouter, Route, Routes} from "react-router";
import Layout from "./ui/components/layout/Layout/Layout.jsx";
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import ProductDetails from "./ui/components/products/ProductDetails/ProductDetails.jsx";
import Register from "./ui/components/auth/Register/Register.jsx";
import Login from "./ui/components/auth/Login/Login.jsx";
import ProtectedRoute from "./ui/components/routing/ProtectedRoute/ProtectedRoute.jsx";
import ShoppingCart from "./ui/components/cart/ShoppingCart/ShoppingCart.jsx";


const App = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/register" element={<Register/>}/>
                <Route path="/login" element={<Login/>}/>
                <Route path="/" element={<Layout/>}>
                    <Route index element={<HomePage/>}/>
                    <Route element={<ProtectedRoute/>}>
                        <Route path="products" element={<ProductsPage/>}/>
                        <Route path="products/:id" element={<ProductDetails/>}/>
                        <Route path="shopping-cart" element={<ShoppingCart/>}/>
                    </Route>

                </Route>
            </Routes>
        </BrowserRouter>
    );
}

export default App;