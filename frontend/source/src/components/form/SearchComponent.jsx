import React from 'react'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Form from 'react-bootstrap/Form'

const SearchComponent = ({ placeholder, onChange, value }) => {

    return (
        <Row>
            <Col xl={4} lg={3} md={2} sm={0} xs={0}></Col>
            <Col xl={4} lg={6} md={8} sm={12}>
                <Form>
                    <Form.Group className="mb-3" controlId="formSearch">
                        <Form.Label visuallyHidden={true}>Buscar</Form.Label>
                        <Form.Control autoComplete="off" onChange={onChange} value={value} type="text" placeholder={placeholder} />
                    </Form.Group>
                </Form>
            </Col>
            <Col xl={4} lg={3} md={2} sm={0} xs={0}></Col>
        </Row>
    )
}

export default SearchComponent