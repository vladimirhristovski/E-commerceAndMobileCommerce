import React from 'react';
import useShoppingCart from "../../../../hooks/useShoppingCart.js";
import {
    Box,
    Button,
    Card,
    CardContent,
    Divider,
    IconButton,
    List,
    ListItem,
    ListItemText,
    Typography,
} from '@mui/material';
import {Delete} from '@mui/icons-material';

const ShoppingCart = () => {
    const {products} = useShoppingCart();

    const getTotal = () =>
        products.reduce((sum, product) => sum + product.price, 0).toFixed(2);

    return (
        <Box my={3} width={500} mx="auto">
            <Card>
                <CardContent>
                    <Typography variant="h5" gutterBottom>
                        Shopping Cart
                    </Typography>
                    <Divider sx={{mb: 2}}/>
                    <List>
                        {products.map(item => (
                            <ListItem
                                key={item.id}
                                secondaryAction={<IconButton edge="end" color="error"><Delete/></IconButton>}
                            >
                                <ListItemText
                                    primary={item.name}
                                    secondary={`$${item.price.toFixed(2)}`}
                                />
                            </ListItem>
                        ))}
                    </List>
                    <Divider sx={{my: 2}}/>
                    <Typography variant="h6">Total: ${getTotal()}</Typography>
                    <Button variant="contained" color="primary" fullWidth sx={{mt: 2}}>
                        Checkout
                    </Button>
                </CardContent>
            </Card>
        </Box>
    );
};

export default ShoppingCart;
