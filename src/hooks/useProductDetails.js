import {useEffect, useState} from "react";
import productRepository from "../repository/productRepository.js";
import categoryRepository from "../repository/categoryRepository.js";
import manufacturerRepository from "../repository/manufacturerRepository.js";

const useProductDetails = (id) => {
    const [state, setState] = useState({
        "product": null,
        "category": null,
        "manufacturer": null,
    });

    useEffect(() => {
        productRepository
            .findById(id)
            .then((response) => {
                setState(prevState => ({...prevState, "product": response.data}));
                categoryRepository
                    .findById(response.data.categoryId)
                    .then((response) => {
                        setState(prevState => ({...prevState, "category": response.data}));
                    })
                    .catch((error) => console.log(error));
                console.log(response.data);
                manufacturerRepository
                    .findById(response.data.manufacturerId)
                    .then((response) => {
                        setState(prevState => ({...prevState, "manufacturer": response.data}));
                    })
                    .catch((error) => console.log(error));
            })
            .catch((error) => console.log(error));
    }, [id]);

    return state;
};

export default useProductDetails;