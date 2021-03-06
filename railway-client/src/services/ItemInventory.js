import axios from 'axios';

export default {
    items: {
        fetchLookupData: payload =>
            axios.get(
                `/item-inventory/lookup/${payload}`)
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
                `/item-inventory/getAll/${payload?.item}/${payload?.inventoryType}/${payload?.pageSize}/${payload?.pageNo}`)
            .then(res => res.data),
    },
};