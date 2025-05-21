import React, {useState} from 'react';
import {Box, Button, CircularProgress} from "@mui/material";
import ProductsGrid from "../../components/products/ProductsGrid/ProductsGrid.jsx";
import useProducts from "../../../hooks/useProducts.js";
import "./ProductsPage.css";
import AddProductDialog from "../../components/products/AddProductDialog/AddProductDialog.jsx";

const ProductsPage = () => {
    const {products, loading, onAdd, onEdit, onDelete} = useProducts();
    const [addProductDialogOpen, setAddProductDialogOpen] = useState(false);

    return (
        <>
            <Box className="products-box">
                {loading && (
                    <Box className="progress-box">
                        <CircularProgress/>
                    </Box>
                )}
                {!loading &&
                    <>
                        <Box sx={{display: "flex", justifyContent: "flex-end", mb: 2}}>
                            <Button variant="contained" color="primary" onClick={() => setAddProductDialogOpen(true)}>
                                Add Product
                            </Button>
                        </Box>
                        <ProductsGrid products={products} onEdit={onEdit} onDelete={onDelete}/>
                    </>}
            </Box>
            <AddProductDialog
                open={addProductDialogOpen}
                onClose={() => setAddProductDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default ProductsPage;