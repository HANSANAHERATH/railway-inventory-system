import axios from 'axios';

export default {
    items: {
        fetchLookupData: payload =>
            axios.get(
                `/item-inventory/lookup?itemName=`)
            .then(res => res.data),
        
        createInventory: payload => 
                axios.post(
                    `/item-inventory/create`,
                    {
                        ...payload
                    }
                )
            .then(res => res.data),
        
        fetchInvenoryList: payload =>
            axios.get(
                `/item-inventory/getAll/${payload}`)
            .then(res => res.data),
    },
};
