import React from 'react';
import HostCard from "../HostCard/HostCard.jsx";
import {Grid} from "@mui/material";

const HostsGrid = ({hosts, onEdit, onDelete}) => {
    return (
        <Grid container spacing={{xs: 2, md: 3}}>
            {hosts.map((host) => (
                <Grid key={host.id} size={{xs: 12, sm: 6, md: 4, lg: 3}}>
                    <HostCard host={host} onEdit={onEdit} onDelete={onDelete}/>
                </Grid>
            ))}
        </Grid>
    );
};

export default HostsGrid;
