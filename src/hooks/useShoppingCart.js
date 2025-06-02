import {useEffect, useState} from "react";
import shoppingCartRepository from "../repository/shoppingCartRepository.js";

const useShoppingCart = () => {
    const [shoppingCart, setShoppingCart] = useState({
        "id": null,
        "dateCreated": null,
        "user": null,
        "products": [],
        "status": null,
    });

    useEffect(() => {
        shoppingCartRepository
            .getActive()
            .then((response) => setShoppingCart(response.data))
            .catch((error) => console.log(error));
    }, []);

    return shoppingCart;
};

export default useShoppingCart;
